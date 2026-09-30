package com.prueba.entities;

import com.prueba.enums.EstadoPoliza;
import com.prueba.enums.TipoPoliza;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class PolizaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoPoliza tipoPoliza;
    @Enumerated(EnumType.STRING)
    private EstadoPoliza estadoPoliza;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Integer canon;
    private BigDecimal prima;
    private String tomador;

    public PolizaEntity() {

    }

    public PolizaEntity(TipoPoliza tipoPoliza, LocalDateTime fechaInicio, LocalDateTime fechaFin, Integer canon, BigDecimal prima, String tomador) {
        this.tipoPoliza = tipoPoliza;
        this.estadoPoliza = EstadoPoliza.ACTIVA;
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
