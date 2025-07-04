package com.two_weeks_backend.two_weeks_backend.entities;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_venta.DetalleVentaShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.Venta;
import com.two_weeks_backend.two_weeks_backend.entities.Producto;
import com.two_weeks_backend.two_weeks_backend.entities.Trabajador;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

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

    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @ManyToOne
    @JoinColumn(name = "venta", nullable = false, foreignKey = @ForeignKey(name = "fk_detalle_venta_venta"))
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "producto", nullable = false, foreignKey = @ForeignKey(name = "fk_detalle_venta_producto"))
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "vendedor", nullable = false, foreignKey = @ForeignKey(name = "fk_detale_venta_vendedor"))
    private Trabajador vendedor;

    @ManyToOne
    @JoinColumn(name = "cobrador", nullable = false, foreignKey = @ForeignKey(name = "fk_detalle_venta_cobrador"))
    private Trabajador cobrador;

    @PrePersist
    private void prePersist() {
        if(this.activated == null) {
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
        detalleVentaShowDTO.setSubTotal(this.getSubTotal());
        detalleVentaShowDTO.setVenta(this.getVenta().asShowDTO());
        detalleVentaShowDTO.setProducto(this.getProducto().asShowDTO());
        detalleVentaShowDTO.setVendedor(this.getVendedor().asShowDTO());
        detalleVentaShowDTO.setCobrador(this.getCobrador().asShowDTO());
        return detalleVentaShowDTO;
    }
}
