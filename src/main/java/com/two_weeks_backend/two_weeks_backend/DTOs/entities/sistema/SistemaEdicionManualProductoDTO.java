package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sistema;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SistemaEdicionManualProductoDTO {
    @NotNull
    private Long id;

    @NotNull
    private Boolean edicionManualProducto;
}
