package com.prueba.dtos;

import com.prueba.enums.TipoEventoCore;

public class CoreEventoDTO {
    private TipoEventoCore evento;
    private Long polizaId;

    public CoreEventoDTO() {
    }

    public CoreEventoDTO(TipoEventoCore evento, Long polizaId) {
        this.evento = evento;
        this.polizaId = polizaId;
    }

    public TipoEventoCore getEvento() {
        return evento;
    }

    public void setEvento(TipoEventoCore evento) {
        this.evento = evento;
    }

    public Long getPolizaId() {
        return polizaId;
    }

    public void setPolizaId(Long polizaId) {
        this.polizaId = polizaId;
    }
}