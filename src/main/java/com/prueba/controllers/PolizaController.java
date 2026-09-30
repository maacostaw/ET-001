package com.prueba.controllers;

import com.prueba.dtos.PolizaDTO;
import com.prueba.dtos.RiesgoDTO;
import com.prueba.dtos.RiesgoRequestDTO;
import com.prueba.enums.EstadoPoliza;
import com.prueba.enums.TipoPoliza;
import com.prueba.services.PolizaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/polizas")
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

    @GetMapping("/{id}/riesgos")
    public ResponseEntity<List<RiesgoDTO>> getRiesgos(@PathVariable Long id) {
        List<RiesgoDTO> riesgos = this.polizaService.getRiesgos(id);
        return ResponseEntity.status(HttpStatus.OK).body(riesgos);
    }

    @PostMapping("/{id}/renovar")
    public ResponseEntity<PolizaDTO> renovar(@PathVariable Long id) {
        PolizaDTO polizaRenovada = polizaService.renovar(id);
        return ResponseEntity.status(HttpStatus.OK).body(polizaRenovada);
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<PolizaDTO> cancelar(@PathVariable Long id) {
        PolizaDTO polizaCancelada = polizaService.cancelar(id);
        return ResponseEntity.status(HttpStatus.OK).body(polizaCancelada);
    }

    @PostMapping("/{id}/riesgos")
    public ResponseEntity<RiesgoDTO> agregarRiesgo(@PathVariable Long id,
                                                   @Valid @RequestBody RiesgoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(polizaService.agregarRiesgo(id, request));
    }
}
