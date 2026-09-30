package com.prueba.services;

import com.prueba.clients.CoreClient;
import com.prueba.dtos.PolizaDTO;
import com.prueba.dtos.RiesgoDTO;
import com.prueba.dtos.RiesgoRequestDTO;
import com.prueba.entities.PolizaEntity;
import com.prueba.entities.RiesgoEntity;
import com.prueba.enums.EstadoPoliza;
import com.prueba.enums.EstadoRiesgo;
import com.prueba.enums.TipoPoliza;
import com.prueba.exceptions.RecursoNoEncontradoException;
import com.prueba.exceptions.ReglaDeNegocioException;
import com.prueba.repositories.PolizaRepository;
import com.prueba.repositories.RiesgoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PolizaService {
    private PolizaRepository polizaRepository;
    private RiesgoRepository riesgoRepository;

    private ModelMapper modelMapper;
    private BigDecimal ipc;
    private CoreClient coreClient;

    public PolizaService(PolizaRepository polizaRepository,
                         ModelMapper modelMapper,
                         RiesgoRepository riesgoRepository,
                         @Value("${poliza.ipc}") BigDecimal ipc,
                         CoreClient coreClient
    ) {
        this.polizaRepository = polizaRepository;
        this.riesgoRepository = riesgoRepository;
        this.modelMapper = modelMapper;
        this.ipc = ipc;
        this.coreClient = coreClient;
    }

    // Requerimiento 1
    public List<PolizaDTO> getByEstadoYTipo(TipoPoliza tipo, EstadoPoliza estado) {
        return polizaRepository.buscarPorTipoYEstado(tipo, estado).stream()
                .map(p -> modelMapper.map(p, PolizaDTO.class))
                .toList();
    }

    // Requerimiento 2
    @Transactional(readOnly = true)
    public List<RiesgoDTO> getRiesgos(Long polizaId) {
        if (!polizaRepository.existsById(polizaId)) {
            throw new RecursoNoEncontradoException("No existe la póliza con id: " + polizaId);
        }
        return riesgoRepository.findByPolizaEntityId(polizaId).stream()
                .map(r -> modelMapper.map(r, RiesgoDTO.class))
                .toList();
    }

    // Requerimiento 3
    @Transactional
    public PolizaDTO renovar(Long id) {
        PolizaEntity poliza = polizaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la póliza con id " + id));

        if (poliza.getEstadoPoliza() == EstadoPoliza.CANCELADA) {
            throw new ReglaDeNegocioException("No se puede renovar una póliza cancelada");
        }

        long meses = ChronoUnit.MONTHS.between(poliza.getFechaInicio(), poliza.getFechaFin());

        BigDecimal nuevoCanon = new BigDecimal(poliza.getCanon())
                .multiply(BigDecimal.ONE.add(ipc))
                .setScale(0, RoundingMode.HALF_UP);

        poliza.setCanon(nuevoCanon.intValueExact());
        poliza.setPrima(nuevoCanon.multiply(BigDecimal.valueOf(meses)));
        poliza.setFechaInicio(poliza.getFechaFin());
        poliza.setFechaFin(poliza.getFechaFin().plusMonths(meses));
        poliza.setEstadoPoliza(EstadoPoliza.RENOVADA);

        PolizaEntity guardada = polizaRepository.save(poliza);

        //Notificación al core client
        coreClient.notificar(guardada.getId());

        return modelMapper.map(guardada, PolizaDTO.class);
    }

    // Requerimiento 4
    @Transactional
    public PolizaDTO cancelar(Long id) {
        PolizaEntity poliza = polizaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la póliza con id " + id));

        if (poliza.getEstadoPoliza() == EstadoPoliza.CANCELADA) {
            throw new ReglaDeNegocioException("La póliza ya está cancelada");
        }

        poliza.setEstadoPoliza(EstadoPoliza.CANCELADA);

        List<RiesgoEntity> riesgos = riesgoRepository.findByPolizaEntityId(id);
        riesgos.forEach(riesgo -> riesgo.setEstadoRiesgo(EstadoRiesgo.CANCELADO));
        riesgoRepository.saveAll(riesgos);

        PolizaEntity guardada = polizaRepository.save(poliza);

        //Notificación al core client
        coreClient.notificar(guardada.getId());

        return modelMapper.map(guardada, PolizaDTO.class);
    }

    //Requerimiento 5
    @Transactional
    public RiesgoDTO agregarRiesgo(Long polizaId, RiesgoRequestDTO request) {
        PolizaEntity poliza = polizaRepository.findById(polizaId)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la póliza con id " + polizaId));

        if (poliza.getTipoPoliza() != TipoPoliza.COLECTIVA) {
            throw new ReglaDeNegocioException("Solo se pueden agregar riesgos a pólizas colectivas");
        }
        if (poliza.getEstadoPoliza() == EstadoPoliza.CANCELADA) {
            throw new ReglaDeNegocioException("No se pueden agregar riesgos a una póliza cancelada");
        }

        RiesgoEntity riesgo = new RiesgoEntity(poliza, request.getAsegurado(), request.getBeneficiario());
        RiesgoEntity guardado = riesgoRepository.save(riesgo);

        //Notificación al core client
        coreClient.notificar(guardado.getId());

        return modelMapper.map(guardado, RiesgoDTO.class);
    }
}
