package com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_compra;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.DetalleCompraEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleCompraShowDTO extends BaseShowDTO<DetalleCompraEntity> {
    private int cantidad;
    private BigDecimal precioCompraUnitario;
    private BigDecimal subTotal;
    private Boolean activated;
    private String producto = "";
    private OffsetDateTime fechaCreacion;
    private OffsetDateTime fechaActualizacion;
}
