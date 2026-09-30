package com.prueba.dtos;

import com.prueba.enums.EstadoPoliza;
import com.prueba.enums.TipoPoliza;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PolizaDTO {
    private Long id;
    private TipoPoliza tipoPoliza;
    private EstadoPoliza estadoPoliza;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Integer canon;
    private BigDecimal prima;
    private String tomador;
    
    public PolizaDTO() {

    }

    public PolizaDTO(Long id, TipoPoliza tipoPoliza, EstadoPoliza estadoPoliza, LocalDateTime fechaInicio, LocalDateTime fechaFin, Integer canon, BigDecimal prima, String tomador) {
        this.id = id;
        this.tipoPoliza = tipoPoliza;
        this.estadoPoliza = estadoPoliza;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.canon = canon;
        this.prima = prima;
        this.tomador = tomador;
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

    public EstadoPoliza getEstadoPoliza() {
        return estadoPoliza;
    }

    public void setEstadoPoliza(EstadoPoliza estadoPoliza) {
        this.estadoPoliza = estadoPoliza;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getCanon() {
        return canon;
    }

    public void setCanon(Integer canon) {
        this.canon = canon;
    }

    public BigDecimal getPrima() {
        return prima;
    }

    public void setPrima(BigDecimal prima) {
        this.prima = prima;
    }

    public String getTomador() {
        return tomador;
    }

    public void setTomador(String tomador) {
        this.tomador = tomador;
    }
}
