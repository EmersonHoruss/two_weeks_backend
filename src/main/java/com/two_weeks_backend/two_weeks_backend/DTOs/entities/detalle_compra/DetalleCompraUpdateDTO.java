package com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_compra;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.DetalleCompraEntity;
import com.two_weeks_backend.two_weeks_backend.entities.ProductoEntity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleCompraUpdateDTO extends BaseUpdateDTO<DetalleCompraEntity> {
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser como mínimo 1")
    private int cantidad;

    @NotNull(message = "El precio de compra unitario es obligatorio")
    @Min(value = 1, message = "El precio de compra unitario debe ser como mínimo 1")
    private BigDecimal precioCompraUnitario;

    @NotNull(message = "El producto es obligatorio")
    private Long productoId;

    @JsonIgnore
    private BigDecimal subTotal;

    public void setAllCalculatedData() {
        this.calculateTotal();
    }

    private void calculateTotal() {
        this.subTotal = this.precioCompraUnitario.multiply(BigDecimal.valueOf(cantidad));
    }

    @Override
    public DetalleCompraEntity asEntity() {
        DetalleCompraEntity detalleCompraEntity = new DetalleCompraEntity();
        detalleCompraEntity.setCantidad(this.getCantidad());
        detalleCompraEntity.setPrecioCompraUnitario(this.getPrecioCompraUnitario());

        if (this.getSubTotal() == null || this.getSubTotal().compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("El sub total no puede ser cero.");
        }
        detalleCompraEntity.setSubTotal(this.getSubTotal());

        ProductoEntity producto = new ProductoEntity();
        producto.setId(this.getProductoId());
        detalleCompraEntity.setProducto(producto);

        return detalleCompraEntity;
    }
}
