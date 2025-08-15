package com.two_weeks_backend.two_weeks_backend.DTOs.entities.caja;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.two_weeks_backend.two_weeks_backend.entities.CajaEntity;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CajaCreateDTO {
    @NotNull(message = "La fecha es obligatoria.")
    @FutureOrPresent(message = "La fecha debe ser presente o futura")
    private OffsetDateTime fecha;

    @NotNull(message = "El monto inicial es obligatorio")
    @Min(message = "El monto inicial debe ser igual o mayor a 0", value = 0)
    private BigDecimal montoInicial;

    public CajaEntity asEntity() {
        CajaEntity caja = new CajaEntity();
        caja.setFecha(this.getFecha());
        caja.setMontoInicial(this.getMontoInicial());
        return caja;
    }
}
