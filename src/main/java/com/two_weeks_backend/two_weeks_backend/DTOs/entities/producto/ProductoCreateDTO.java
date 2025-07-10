package com.two_weeks_backend.two_weeks_backend.DTOs.entities.producto;

import java.math.BigDecimal;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.DistribuidorEntity;
import com.two_weeks_backend.two_weeks_backend.entities.ProductoEntity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoCreateDTO extends BaseCreateDTO<ProductoEntity> {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El precio de venta al por menor es obligatorio")
    @Min(value = 0, message = "El precio de venta al por menor debe ser como mínimo 0")
    private BigDecimal precioVentaMenor;

    @NotNull(message = "El precio de venta al por mayor es obligatorio")
    @Min(value = 0, message = "El precio de venta al por mayor debe ser como mínimo 0")
    private BigDecimal precioVentaMayor;

    @NotNull(message = "El precio de venta al super por mayor es obligatorio")
    @Min(value = 0, message = "El precio de venta al super por mayor debe ser como mínimo 0")
    private BigDecimal precioVentaSuperMayor;

    @NotBlank(message = "El codigo es obligatorio")
    private String codigo;

    @NotNull(message = "El distribuidor es obligatorio")
    private Long distribuidorId;

    @Override
    public ProductoEntity asEntity() {
        ProductoEntity producto = new ProductoEntity();
        producto.setNombre(this.getNombre());
        producto.setPrecioCompra(BigDecimal.ZERO);
        producto.setPrecioVentaMenor(this.getPrecioVentaMenor());
        producto.setPrecioVentaMayor(this.getPrecioVentaMayor());
        producto.setPrecioVentaSuperMayor(this.getPrecioVentaSuperMayor());
        producto.setCodigo(this.getCodigo());
        producto.setStock(0);

        DistribuidorEntity distribuidor = new DistribuidorEntity();
        distribuidor.setId(this.getDistribuidorId());
        producto.setDistribuidor(distribuidor);

        return producto;
    }

}
