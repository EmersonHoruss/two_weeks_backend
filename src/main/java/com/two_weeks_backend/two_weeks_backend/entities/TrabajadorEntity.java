package com.two_weeks_backend.two_weeks_backend.entities;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.trabajador.TrabajadorShowDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private String celular;

    @Column(name = "whatsapp", nullable = false)
    private String whatsapp;

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
    public TrabajadorShowDTO asShowDTO() {
        TrabajadorShowDTO trabajadorShowDTO = new TrabajadorShowDTO();
        trabajadorShowDTO.setId(this.getId());
        trabajadorShowDTO.setActivated(this.getActivated());
        trabajadorShowDTO.setNombre(this.getNombre());
        trabajadorShowDTO.setSexo(this.getSexo().getValue());
        trabajadorShowDTO.setDni(this.getDni());
        trabajadorShowDTO.setDireccion(this.getDireccion());
        trabajadorShowDTO.setCelular(this.getCelular());
        trabajadorShowDTO.setWhatsapp(this.getWhatsapp());
        return trabajadorShowDTO;
    }
}
