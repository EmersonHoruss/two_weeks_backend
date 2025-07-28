package com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_compra;

import java.math.BigDecimal;

import com.two_weeks_backend.two_weeks_backend.entities.DetalleCompraEntity;
import com.two_weeks_backend.two_weeks_backend.entities.ProductoEntity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleCompraUpdateDTO {
    private Long id;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser como mínimo 1")
    private int cantidad;

    @NotNull(message = "El precio de compra unitario es obligatorio")
    @Min(value = 1, message = "El precio de compra unitario debe ser como mínimo 1")
    private BigDecimal precioCompraUnitario;

    @NotNull(message = "El producto es obligatorio")
    private Long productoId;

    private Boolean activated;

    public DetalleCompraEntity asEntity() {
        DetalleCompraEntity detalleCompraEntity = new DetalleCompraEntity();
        if (id != null) {
            detalleCompraEntity.setId(id);
        }
        detalleCompraEntity.setCantidad(this.getCantidad());
        detalleCompraEntity.setPrecioCompraUnitario(this.getPrecioCompraUnitario());
        if (activated != null) {
            detalleCompraEntity.setActivated(activated);
        }

        ProductoEntity producto = new ProductoEntity();
        producto.setId(this.getProductoId());
        detalleCompraEntity.setProducto(producto);

        return detalleCompraEntity;
    }
}
