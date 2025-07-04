package com.two_weeks_backend.two_weeks_backend.entities;

import com.two_weeks_backend.two_weeks_backend.DTOs.producto.ProductoShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductoEntity extends BaseEntity<ProductoShowDTO> {
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio_venta_menor", nullable = false)
    private BigDecimal precioVentaMenor;

    @Column(name = "precio_venta_mayor", nullable = false)
    private BigDecimal precioVentaMayor;

    @Column(name = "precio_venta_super_mayor", nullable = false)
    private BigDecimal precioVentaSuperMayor;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "codigo", nullable = true)
    private String codigo;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @PrePersist
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }
    }

    @Override
    public ProductoShowDTO asShowDTO() {
        ProductoShowDTO productoShowDTO = new ProductoShowDTO();
        productoShowDTO.setId(this.getId());
        productoShowDTO.setActivated(this.getActivated());
        productoShowDTO.setNombre(this.getNombre());
        productoShowDTO.setPrecioVentaMenor(this.getPrecioVentaMenor());
        productoShowDTO.setPrecioVentaMayor(this.getPrecioVentaMayor());
        productoShowDTO.setPrecioVentaSuperMayor(this.getPrecioVentaSuperMayor());
        productoShowDTO.setStock(this.getStock());
        productoShowDTO.setCodigo(this.getCodigo());
        return productoShowDTO;
    }
}
