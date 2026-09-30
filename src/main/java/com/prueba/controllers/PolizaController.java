package com.prueba.controllers;

import com.prueba.dtos.PolizaDTO;
import com.prueba.enums.EstadoPoliza;
import com.prueba.enums.TipoPoliza;
import com.prueba.services.PolizaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/polizas")
public class PolizaController {
    private PolizaService polizaService;

    public PolizaController(PolizaService polizaService) {
        this.polizaService = polizaService;
    }

    @GetMapping
    public ResponseEntity<List<PolizaDTO>> getAll(
            @RequestParam(required = false) TipoPoliza tipo,
            @RequestParam(required = false)EstadoPoliza estado
            ) {
        List<PolizaDTO> polizas = this.polizaService.getByEstadoYTipo(tipo, estado);
        return ResponseEntity.status(HttpStatus.OK).body(polizas);
    }
}
