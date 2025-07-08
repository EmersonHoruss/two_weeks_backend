package com.two_weeks_backend.two_weeks_backend.entities;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.distribuidor.DistribuidorShowDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "distribuidor", uniqueConstraints = { @UniqueConstraint(name = "uk_duenio", columnNames = "duenio") })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DistribuidorEntity extends BaseEntity<DistribuidorShowDTO> {
    @Column(name = "duenio", nullable = false, unique = true)
    private String duenio;

    @Column(name = "duenio_celular", nullable = true)
    private String duenioCelular;

    @Column(name = "duenio_whatsapp", nullable = true)
    private String duenioWhatsapp;

    @Column(name = "empresa_nombre", nullable = true)
    private String empresaNombre;

    @Column(name = "empresa_ruc", nullable = true)
    private String empresaRuc;

    @Column(name = "empresa_celular", nullable = true)
    private String empresaCelular;

    @Column(name = "empresa_giro", nullable = true)
    private String empresaGiro;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @PrePersist
    @SuppressWarnings("unused")
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }
    }

    @Override
    public DistribuidorShowDTO asShowDTO() {
        DistribuidorShowDTO distribuidorShowDTO = new DistribuidorShowDTO();
        distribuidorShowDTO.setId(this.getId());
        distribuidorShowDTO.setActivated(this.getActivated());
        distribuidorShowDTO.setDuenio(this.getDuenio());
        distribuidorShowDTO.setDuenioCelular(this.getDuenioCelular());
        distribuidorShowDTO.setDuenioWhatsapp(this.getDuenioWhatsapp());
        distribuidorShowDTO.setEmpresaNombre(this.getEmpresaNombre());
        distribuidorShowDTO.setEmpresaRuc(this.getEmpresaRuc());
        distribuidorShowDTO.setEmpresaGiro(this.getEmpresaGiro());
        return distribuidorShowDTO;
    }
}
