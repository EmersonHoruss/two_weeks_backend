package com.two_weeks_backend.two_weeks_backend.entities;

import com.two_weeks_backend.two_weeks_backend.DTOs.detalle_compra.DetalleCompraShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.Compra;
import com.two_weeks_backend.two_weeks_backend.entities.Producto;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

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

    @ManyToOne
    @JoinColumn(name = "producto", nullable = false, foreignKey = @ForeignKey(name = "fk_detalle_compra_producto"))
    private Producto producto;
    
    @ManyToOne
    @JoinColumn(name = "compra", nullable = false, foreignKey = @ForeignKey(name = "fk_detalle_compra_compra"))
    private Compra compra;

    @PrePersist
    private void prePersist() {
        if(this.activated == null) {
            this.activated = true;
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
        detalleCompraShowDTO.setProducto(this.getProducto.asShowDTO());
        return detalleCompraShowDTO;
    }
}
