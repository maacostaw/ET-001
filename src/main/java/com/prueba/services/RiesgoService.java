package com.prueba.services;

import com.prueba.dtos.RiesgoDTO;
import com.prueba.entities.RiesgoEntity;
import com.prueba.enums.EstadoRiesgo;
import com.prueba.enums.TipoPoliza;
import com.prueba.exceptions.RecursoNoEncontradoException;
import com.prueba.exceptions.ReglaDeNegocioException;
import com.prueba.repositories.RiesgoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RiesgoService {
    private final RiesgoRepository riesgoRepository;
    private final ModelMapper modelMapper;

    public RiesgoService(RiesgoRepository riesgoRepository, ModelMapper modelMapper) {
        this.riesgoRepository = riesgoRepository;
        this.modelMapper = modelMapper;
    }

    // Requerimiento 6
    @Transactional
    public RiesgoDTO cancelar(Long id) {
        RiesgoEntity riesgo = riesgoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el riesgo con id " + id));

        if (riesgo.getPolizaEntity().getTipoPoliza() != TipoPoliza.COLECTIVA) {
            throw new ReglaDeNegocioException(
                    "Solo se pueden cancelar riesgos de pólizas colectivas; para una individual, cancele la póliza");
        }
        if (riesgo.getEstadoRiesgo() == EstadoRiesgo.CANCELADO) {
            throw new ReglaDeNegocioException("El riesgo ya está cancelado");
        }

        riesgo.setEstadoRiesgo(EstadoRiesgo.CANCELADO);
        RiesgoEntity guardado = riesgoRepository.save(riesgo);
        return modelMapper.map(guardado, RiesgoDTO.class);
    }
}