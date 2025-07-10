package com.two_weeks_backend.two_weeks_backend.entities;

import java.time.OffsetTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SistemaEntity {
    private String nombreEmpresa;

    private String ruc;

    private Boolean permitirCambioDePrecioDeCompra;

    private OffsetTime abre;

    private OffsetTime cierra;

    private String duenio;

    private String duenioCelular;
}
