package com.two_weeks_backend.two_weeks_backend.entities;

import java.math.BigDecimal;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.producto.ProductoShowDTO;

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
@Table(name = "producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductoEntity extends BaseEntity<ProductoShowDTO> {
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "nombre_unico", nullable = false)
    private String nombreUnico;

    @Column(name = "precio_compra", nullable = false)
    private BigDecimal precioCompra;

    @Column(name = "precio_venta_menor", nullable = false)
    private BigDecimal precioVentaMenor;

    @Column(name = "precio_venta_mayor", nullable = false)
    private BigDecimal precioVentaMayor;

    @Column(name = "precio_venta_super_mayor", nullable = false)
    private BigDecimal precioVentaSuperMayor;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @ManyToOne
    @JoinColumn(name = "distribuidor", nullable = false, foreignKey = @ForeignKey(name = "fk_producto_distribuidor"))
    private DistribuidorEntity distribuidor;

    @PrePersist
    @SuppressWarnings("unused")
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }
        this.nombreUnico = this.nombre + " (" + this.distribuidor.getEmpresaNombre() + ")";
    }

    @Override
    public ProductoShowDTO asShowDTO() {
        ProductoShowDTO productoShowDTO = new ProductoShowDTO();
        productoShowDTO.setId(this.getId());
        productoShowDTO.setActivated(this.getActivated());
        productoShowDTO.setNombre(this.getNombre());
        productoShowDTO.setNombreUnico(this.getNombreUnico());
        productoShowDTO.setPrecioCompra(this.getPrecioCompra());
        productoShowDTO.setPrecioVentaMenor(this.getPrecioVentaMenor());
        productoShowDTO.setPrecioVentaMayor(this.getPrecioVentaMayor());
        productoShowDTO.setPrecioVentaSuperMayor(this.getPrecioVentaSuperMayor());
        productoShowDTO.setStock(this.getStock());
        productoShowDTO.setCodigo(this.getCodigo());
        return productoShowDTO;
    }
}
