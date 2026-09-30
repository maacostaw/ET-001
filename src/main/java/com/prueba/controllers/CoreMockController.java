package com.prueba.controllers;

import com.prueba.dtos.CoreEventoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core-mock")
public class CoreMockController {
    private static final Logger log = LoggerFactory.getLogger(CoreMockController.class);

    @PostMapping("/evento")
    public ResponseEntity<Void> recibirEvento(@RequestBody CoreEventoDTO evento) {
        log.info("[CORE-MOCK] Evento recibido: {} para póliza {}", evento.getEvento(), evento.getPolizaId());
        return ResponseEntity.accepted().build();
    }
}