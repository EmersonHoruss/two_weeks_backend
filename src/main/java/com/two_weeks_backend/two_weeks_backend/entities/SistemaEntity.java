package com.two_weeks_backend.two_weeks_backend.entities;

import java.time.OffsetTime;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sistema.SistemaShowDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sistema")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SistemaEntity extends BaseEntity<SistemaShowDTO> {
    @Column(name = "empresa_nombre", nullable = false)
    private String empresaNombre;

    @Column(name = "empresa_ruc", nullable = true)
    private String empresaRuc;

    @Column(name = "empresa_duenio", nullable = true)
    private String empresaDuenio;

    @Column(name = "duenio_celular", nullable = true)
    private String duenioCelular;

    @Column(name = "abre", nullable = true)
    private OffsetTime abre;

    @Column(name = "cierra", nullable = true)
    private OffsetTime cierra;

    @Override
    public SistemaShowDTO asShowDTO() {
        SistemaShowDTO sistemaShowDTO = new SistemaShowDTO();
        sistemaShowDTO.setId(this.getId());
        sistemaShowDTO.setEmpresaNombre(this.getEmpresaNombre());
        sistemaShowDTO.setEmpresaRuc(this.getEmpresaRuc());
        sistemaShowDTO.setEmpresaDuenio(this.getEmpresaDuenio());
        sistemaShowDTO.setDuenioCelular(this.getDuenioCelular());
        sistemaShowDTO.setAbre(this.getAbre());
        sistemaShowDTO.setCierra(this.getCierra());
        return sistemaShowDTO;
    }
}
