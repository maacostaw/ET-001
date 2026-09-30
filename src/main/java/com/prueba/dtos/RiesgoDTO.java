package com.prueba.dtos;

import com.prueba.enums.EstadoRiesgo;

public class RiesgoDTO {
    private Long id;
    private String asegurado;
    private String beneficiario;
    private EstadoRiesgo estadoRiesgo;
    private Long polizaId;

    public RiesgoDTO() {
    }

    public RiesgoDTO(Long id, String asegurado, String beneficiario, EstadoRiesgo estadoRiesgo, Long polizaId) {
        this.id = id;
        this.asegurado = asegurado;
        this.beneficiario = beneficiario;
        this.estadoRiesgo = estadoRiesgo;
        this.polizaId = polizaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAsegurado() {
        return asegurado;
    }

    public void setAsegurado(String asegurado) {
        this.asegurado = asegurado;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public EstadoRiesgo getEstadoRiesgo() {
        return estadoRiesgo;
    }

    public void setEstadoRiesgo(EstadoRiesgo estadoRiesgo) {
        this.estadoRiesgo = estadoRiesgo;
    }

    public Long getPolizaId() {
        return polizaId;
    }

    public void setPolizaId(Long polizaId) {
        this.polizaId = polizaId;
    }
}
