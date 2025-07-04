package com.two_weeks_backend.two_weeks_backend.entities;

import com.two_weeks_backend.two_weeks_backend.DTOs.trabajador.TrabajadorShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trabajador")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrabajadorEntity extends BaseEntity<TrabajadorShowDTO> {
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = true)
    private SexoEnum sexo;

    @Column(name = "dni", nullable = false)
    private String dni;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "numero_celular", nullable = false)
    private String numeroCelular;

    @Column(name = "whatsapp", nullable = false)
    private String whatsapp;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @PrePersist
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }
    }

    @Override
    public TrabajadorShowDTO asShowDTO() {
        TrabajadorShowDTO trabajadorShowDTO = new TrabajadorShowDTO();
        trabajadorShowDTO.setId(this.getId());
        trabajadorShowDTO.setActivated(this.getActivated());
        trabajadorShowDTO.setNombre(this.getNombre());
        trabajadorShowDTO.setSexo(this.getSexo());
        trabajadorShowDTO.setDni(this.getDni());
        trabajadorShowDTO.setDireccion(this.getDireccion());
        trabajadorShowDTO.setNumeroCelular(this.getNumeroCelular());
        trabajadorShowDTO.setWhatsapp(this.getWhatsapp());
        return trabajadorShowDTO;
    }
}

public enum SexoEnum {
    MASCULINO("Masculino"),
    FEMENINO("Femenino");

    private String value;

    SexoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
