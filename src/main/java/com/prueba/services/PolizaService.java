package com.prueba.services;

import com.prueba.dtos.PolizaDTO;
import com.prueba.enums.EstadoPoliza;
import com.prueba.enums.TipoPoliza;
import com.prueba.repositories.PolizaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolizaService {
    private PolizaRepository polizaRepository;

    private ModelMapper modelMapper;

    public PolizaService(PolizaRepository polizaRepository, ModelMapper modelMapper) {
        this.polizaRepository = polizaRepository;
        this.modelMapper = modelMapper;
    }

    /**
    public List<PolizaDTO> getAll() {
        return polizaRepository.findAll().stream()
                .map(polizaEntity -> modelMapper.map(polizaEntity, PolizaDTO.class))
                .toList();
    }
     */

    public List<PolizaDTO> getByEstadoYTipo(TipoPoliza tipo, EstadoPoliza estado) {
        return polizaRepository.buscarPorTipoYEstado(tipo, estado).stream()
                .map(polizaEntity -> modelMapper.map(polizaEntity, PolizaDTO.class))
                .toList();
    }
}
