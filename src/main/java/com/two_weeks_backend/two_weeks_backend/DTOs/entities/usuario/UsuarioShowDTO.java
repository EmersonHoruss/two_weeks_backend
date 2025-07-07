package com.two_weeks_backend.two_weeks_backend.DTOs.entities.usuario;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.UsuarioEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioShowDTO extends BaseShowDTO<UsuarioEntity> {
    private String nombre;
    private String correo;
    private String roles;
    private Boolean activated;
}
