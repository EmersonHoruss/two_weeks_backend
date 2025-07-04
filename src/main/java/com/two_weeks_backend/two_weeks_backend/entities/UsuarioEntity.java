package com.two_weeks_backend.two_weeks_backend.entities;

import com.two_weeks_backend.two_weeks_backend.DTOs.usuario.UsuarioShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

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
        usuarioShowDTO.setContrasenia(this.getContrasenia());
        usuarioShowDTO.setRoles(this.getRoles());
        return usuarioShowDTO;
    }
}
