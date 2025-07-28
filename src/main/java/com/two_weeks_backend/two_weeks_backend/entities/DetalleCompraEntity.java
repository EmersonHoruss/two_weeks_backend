package com.two_weeks_backend.two_weeks_backend.entities;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_compra.DetalleCompraShowDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "detalle_compra")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetalleCompraEntity extends BaseEntity<DetalleCompraShowDTO> {
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "precio_compra_unitario", nullable = false)
    private BigDecimal precioCompraUnitario;

    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @Column(name = "fecha_creacion", nullable = false)
    private OffsetDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = true)
    private OffsetDateTime fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "producto", nullable = false, foreignKey = @ForeignKey(name = "fk_detalle_compra_producto"))
    private ProductoEntity producto;

    @ManyToOne
    @JoinColumn(name = "compra", nullable = false, foreignKey = @ForeignKey(name = "fk_detalle_compra_compra"))
    private CompraEntity compra;

    @PrePersist
    @SuppressWarnings("unused")
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }
        if (this.getSubTotal() == null || this.getSubTotal().compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("El sub total no puede ser cero.");
        }
    }

    @Override
    public DetalleCompraShowDTO asShowDTO() {
        DetalleCompraShowDTO detalleCompraShowDTO = new DetalleCompraShowDTO();
        detalleCompraShowDTO.setId(this.getId());
        detalleCompraShowDTO.setActivated(this.getActivated());
        detalleCompraShowDTO.setCantidad(this.getCantidad());
        detalleCompraShowDTO.setPrecioCompraUnitario(this.getPrecioCompraUnitario());
        detalleCompraShowDTO.setSubTotal(this.getSubTotal());
        detalleCompraShowDTO.setProducto(this.getProducto().getNombre());
        detalleCompraShowDTO.setFechaCreacion(this.getFechaCreacion());
        detalleCompraShowDTO.setFechaActualizacion(this.getFechaActualizacion());
        return detalleCompraShowDTO;
    }

    public void calculateSubTotal() {
        this.subTotal = this.precioCompraUnitario.multiply(BigDecimal.valueOf(cantidad));
    }

    public boolean isActivo() {
        if (this.activated == null)
            return true;
        return this.activated;
    }
}
