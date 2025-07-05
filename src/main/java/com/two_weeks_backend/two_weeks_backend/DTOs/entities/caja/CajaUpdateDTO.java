package com.two_weeks_backend.two_weeks_backend.DTOs.entities.caja;

import com.two_weeks_backend.two_weeks_backend.entities.Caja;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class CajaUpdateDTO {
    @NotNull
    private Long id;

    @NotNull
    private BigDecimal montoInicial;

    public Caja asEntity() {
        Caja caja = new Caja();
        caja.setId(this.getId());
        caja.setMontoInicial(this.getMontoINicial());
        return caja;
    }
}
