package com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraArrivedDTO {
    @NotNull
    private Long id;

    @NotNull
    private Boolean llego;

    private OffsetDateTime llegoFecha;
}
