package com.prueba.dtos;

import com.prueba.enums.TipoPoliza;

public class PolizaDTO {
    private Long id;
    private TipoPoliza tipoPoliza;

    public PolizaDTO() {
    }

    public PolizaDTO(Long id, TipoPoliza tipoPoliza) {
        this.id = id;
        this.tipoPoliza = tipoPoliza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPoliza getTipoPoliza() {
        return tipoPoliza;
    }

    public void setTipoPoliza(TipoPoliza tipoPoliza) {
        this.tipoPoliza = tipoPoliza;
    }
}
