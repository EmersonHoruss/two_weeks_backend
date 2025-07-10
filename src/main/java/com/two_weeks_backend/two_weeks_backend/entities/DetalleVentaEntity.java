package com.two_weeks_backend.two_weeks_backend.entities;

import java.math.BigDecimal;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_venta.DetalleVentaShowDTO;

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
@Table(name = "detalle_venta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetalleVentaEntity extends BaseEntity<DetalleVentaShowDTO> {
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "precio_venta", nullable = false)
    private BigDecimal precioVenta;

    @Column(name = "precio_compra", nullable = false)
    private BigDecimal precioCompra;

    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @ManyToOne
    @JoinColumn(name = "venta", nullable = false, foreignKey = @ForeignKey(name = "fk_detalle_venta_venta"))
    private VentaEntity venta;

    @ManyToOne
    @JoinColumn(name = "producto", nullable = false, foreignKey = @ForeignKey(name = "fk_detalle_venta_producto"))
    private ProductoEntity producto;

    @ManyToOne
    @JoinColumn(name = "vendedor", nullable = false, foreignKey = @ForeignKey(name = "fk_detale_venta_vendedor"))
    private TrabajadorEntity vendedor;

    @ManyToOne
    @JoinColumn(name = "cobrador", nullable = false, foreignKey = @ForeignKey(name = "fk_detalle_venta_cobrador"))
    private TrabajadorEntity cobrador;

    @PrePersist
    @SuppressWarnings("unused")
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }
    }

    @Override
    public DetalleVentaShowDTO asShowDTO() {
        DetalleVentaShowDTO detalleVentaShowDTO = new DetalleVentaShowDTO();
        detalleVentaShowDTO.setId(this.getId());
        detalleVentaShowDTO.setActivated(this.getActivated());
        detalleVentaShowDTO.setCantidad(this.getCantidad());
        detalleVentaShowDTO.setPrecioVenta(this.getPrecioVenta());
        detalleVentaShowDTO.setPrecioCompra(this.getPrecioCompra());
        detalleVentaShowDTO.setSubTotal(this.getSubTotal());
        detalleVentaShowDTO.setProducto(this.getProducto().getNombre());
        detalleVentaShowDTO.setVendedor(this.getVendedor().getNombre());
        detalleVentaShowDTO.setCobrador(this.getCobrador().getNombre());
        return detalleVentaShowDTO;
    }
}
