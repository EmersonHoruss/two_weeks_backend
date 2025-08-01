package com.two_weeks_backend.two_weeks_backend.DTOs.entities.caja;

import java.math.BigDecimal;

import com.two_weeks_backend.two_weeks_backend.entities.CajaEntity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CajaUpdateDTO {
    @NotNull
    private Long id;

    @NotNull
    private BigDecimal montoInicial;

    public CajaEntity asEntity() {
        CajaEntity caja = new CajaEntity();
        caja.setId(this.getId());
        caja.setMontoInicial(this.getMontoInicial());
        return caja;
    }
}
