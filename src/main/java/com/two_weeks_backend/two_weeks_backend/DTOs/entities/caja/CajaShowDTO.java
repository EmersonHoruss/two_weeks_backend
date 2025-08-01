package com.two_weeks_backend.two_weeks_backend.DTOs.entities.caja;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.*;

@Getter
@Setter
public class CajaShowDTO {
    private Long id;
    private OffsetDateTime fecha;
    private BigDecimal montoInicial;
    private BigDecimal montoFinalFisico;
    private BigDecimal montoFinalDigital;
    private BigDecimal ganancia;
}
