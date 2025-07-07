package com.two_weeks_backend.two_weeks_backend.entities;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.usuario.UsuarioShowDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioEntity extends BaseEntity<UsuarioShowDTO> {
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;

    @Column(name = "roles", nullable = false)
    private String roles;

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
    public UsuarioShowDTO asShowDTO() {
        UsuarioShowDTO usuarioShowDTO = new UsuarioShowDTO();
        usuarioShowDTO.setId(this.getId());
        usuarioShowDTO.setActivated(this.getActivated());
        usuarioShowDTO.setNombre(this.getNombre());
        usuarioShowDTO.setCorreo(this.getCorreo());
        usuarioShowDTO.setRoles(this.getRoles());
        return usuarioShowDTO;
    }
}
