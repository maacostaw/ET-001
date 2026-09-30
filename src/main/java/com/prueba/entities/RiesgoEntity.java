package com.prueba.entities;

import com.prueba.enums.EstadoRiesgo;
import jakarta.persistence.*;

@Entity
public class RiesgoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String asegurado;
    private String beneficiario;
    private EstadoRiesgo estadoRiesgo;
    @ManyToOne
    @JoinColumn(name="poliza_id")
    private PolizaEntity polizaEntity;

    public RiesgoEntity(){

    }

    public RiesgoEntity(PolizaEntity polizaEntity, EstadoRiesgo estadoRiesgo, String beneficiario, String asegurado, Long id) {
        this.polizaEntity = polizaEntity;
        this.estadoRiesgo = estadoRiesgo;
        this.beneficiario = beneficiario;
        this.asegurado = asegurado;
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

    public PolizaEntity getPolizaEntity() {
        return polizaEntity;
    }

    public void setPolizaEntity(PolizaEntity polizaEntity) {
        this.polizaEntity = polizaEntity;
    }
}
