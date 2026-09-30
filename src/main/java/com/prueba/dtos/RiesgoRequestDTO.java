package com.prueba.dtos;

import jakarta.validation.constraints.NotBlank;

public class RiesgoRequestDTO {
    @NotBlank(message = "El asegurado es obligatorio")
    private String asegurado;

    @NotBlank(message = "El beneficiario es obligatorio")
    private String beneficiario;

    public RiesgoRequestDTO() {
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
}
