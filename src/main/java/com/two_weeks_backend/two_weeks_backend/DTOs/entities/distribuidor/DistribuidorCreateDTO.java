package com.two_weeks_backend.two_weeks_backend.DTOs.entities.distribuidor;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.DistribuidorEntity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistribuidorCreateDTO extends BaseCreateDTO<DistribuidorEntity> {
    @NotEmpty(message = "Nombre del dueño de la empresa es obligatorio")
    private String duenio;

    private String duenioCelular;

    private String duenioWhatsapp;

    private String empresaNombre;

    @Pattern(regexp = "\\d{11}", message = "El RUC debe tener exactamente 11 dígitos numéricos")
    private String empresaRuc;

    private String empresaGiro;

    @Override
    public DistribuidorEntity asEntity() {
        DistribuidorEntity distribuidor = new DistribuidorEntity();
        distribuidor.setDuenio(this.getDuenio());
        distribuidor.setDuenioCelular(this.getDuenioCelular());
        distribuidor.setDuenioWhatsapp(this.getDuenioWhatsapp());
        distribuidor.setEmpresaNombre(this.getEmpresaNombre());
        distribuidor.setEmpresaRuc(this.getEmpresaRuc());
        distribuidor.setEmpresaGiro(this.getEmpresaGiro());
        return distribuidor;
    }
}
