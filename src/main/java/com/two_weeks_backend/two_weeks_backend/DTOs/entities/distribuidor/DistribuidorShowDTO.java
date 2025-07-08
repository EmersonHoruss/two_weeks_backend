package com.two_weeks_backend.two_weeks_backend.DTOs.entities.distribuidor;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.DistribuidorEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistribuidorShowDTO extends BaseShowDTO<DistribuidorEntity> {
    private String duenio;
    private String duenioCelular;
    private String duenioWhatsapp;
    private String empresaNombre;
    private String empresaRuc;
    private String empresaGiro;
    private Boolean activated;
}
