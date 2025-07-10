package com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_venta;

import java.math.BigDecimal;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.DetalleCompraEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleVentaShowDTO extends BaseShowDTO<DetalleCompraEntity> {
    private int cantidad;
    private BigDecimal precioVenta;
    private BigDecimal precioCompra;
    private BigDecimal subTotal;
    private String producto = "";
    private String vendedor = "";
    private String cobrador = "";
    private Boolean activated;
}
