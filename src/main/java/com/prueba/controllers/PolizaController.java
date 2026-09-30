package com.prueba.controllers;

import com.prueba.dtos.PolizaDTO;
import com.prueba.services.PolizaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<List<PolizaDTO>> getAll() {
        List<PolizaDTO> polizas = this.polizaService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(polizas);
    }
}
