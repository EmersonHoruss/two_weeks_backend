package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sistema;

import java.time.OffsetTime;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.SistemaEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SistemaUpdateDTO extends BaseUpdateDTO<SistemaEntity> {
    @NotBlank(message = "El nombre de la empresa es obligatorio")
    private String empresaNombre;

    @Pattern(regexp = "\\d{11}", message = "El RUC debe tener exactamente 11 dígitos numéricos")
    private String empresaRuc;

    private String empresaDuenio;

    @Pattern(regexp = "\\d{11}", message = "El celular debe tener 9 dígitos numéricos")
    private String duenioCelular;

    private OffsetTime abre;

    private OffsetTime cierra;

    @Override
    public SistemaEntity asEntity() {
        SistemaEntity sistemaEntity = new SistemaEntity();
        sistemaEntity.setId(this.getId());
        sistemaEntity.setEmpresaNombre(this.getEmpresaNombre());
        sistemaEntity.setEmpresaRuc(this.getEmpresaRuc());
        sistemaEntity.setEmpresaDuenio(this.getEmpresaDuenio());
        sistemaEntity.setAbre(this.getAbre());
        sistemaEntity.setCierra(this.getCierra());
        return sistemaEntity;
    }
}
