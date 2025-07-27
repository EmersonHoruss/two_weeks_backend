package com.two_weeks_backend.two_weeks_backend.DTOs.entities.producto;

import java.math.BigDecimal;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.DistribuidorEntity;
import com.two_weeks_backend.two_weeks_backend.entities.ProductoEntity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoUpdateDTO extends BaseUpdateDTO<ProductoEntity> {
    @NotNull(message = "El precio de venta al por menor es obligatorio")
    @Min(value = 0, message = "El precio de venta al por menor debe ser como mínimo 0")
    private BigDecimal precioVentaMenor;

    @NotNull(message = "El precio de venta al por mayor es obligatorio")
    @Min(value = 0, message = "El precio de venta al por mayor debe ser como mínimo 0")
    private BigDecimal precioVentaMayor;

    @NotNull(message = "El precio de venta al super por mayor es obligatorio")
    @Min(value = 0, message = "El precio de venta al super por mayor debe ser como mínimo 0")
    private BigDecimal precioVentaSuperMayor;

    @NotNull(message = "El distribuidor es obligatorio")
    private Long distribuidorId;

    @Override
    public ProductoEntity asEntity() {
        ProductoEntity producto = new ProductoEntity();
        producto.setPrecioVentaMenor(this.getPrecioVentaMenor());
        producto.setPrecioVentaMayor(this.getPrecioVentaMayor());
        producto.setPrecioVentaSuperMayor(this.getPrecioVentaSuperMayor());

        DistribuidorEntity distribuidor = new DistribuidorEntity();
        distribuidor.setId(this.getDistribuidorId());
        producto.setDistribuidor(distribuidor);

        return producto;
    }
}
