package com.two_weeks_backend.two_weeks_backend.DTOs.entities.producto;

import java.math.BigDecimal;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.ProductoEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoShowDTO extends BaseShowDTO<ProductoEntity> {
    private String nombre;
    private String nombreUnico;
    private BigDecimal precioCompra;
    private BigDecimal precioVentaMenor;
    private BigDecimal precioVentaMayor;
    private BigDecimal precioVentaSuperMayor;
    private int stock;
    private String codigo;
    private Boolean activated;
}
