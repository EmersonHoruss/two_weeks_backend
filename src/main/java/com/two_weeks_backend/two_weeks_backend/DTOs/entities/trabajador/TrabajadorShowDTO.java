package com.two_weeks_backend.two_weeks_backend.DTOs.entities.trabajador;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.TrabajadorEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrabajadorShowDTO extends BaseShowDTO<TrabajadorEntity> {
    private String nombre;
    private String sexo;
    private String dni;
    private String direccion;
    private String celular;
    private String whatsapp;
    private Boolean activated;
}
