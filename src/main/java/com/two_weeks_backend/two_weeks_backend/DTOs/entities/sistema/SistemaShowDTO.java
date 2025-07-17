package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sistema;

import java.time.OffsetTime;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.SistemaEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SistemaShowDTO extends BaseShowDTO<SistemaEntity> {
    private Long id;
    private String empresaNombre;
    private String empresaRuc;
    private String empresaDuenio;
    private String duenioCelular;
    private OffsetTime abre;
    private OffsetTime cierra;
    private Boolean edicionManualProducto;
}
