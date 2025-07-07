package com.two_weeks_backend.two_weeks_backend.entities;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraShowDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "compra")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompraEntity extends BaseEntity<CompraShowDTO> {
    @Column(name = "distribuidor", nullable = false)
    private String distribuidor;

    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;

    @Column(name = "flete", nullable = true)
    private BigDecimal flete;

    @Column(name = "otros_gastos_cantidad", nullable = true)
    private BigDecimal otrosGastosCantidad;

    @Column(name = "otros_gastos_detalle", nullable = true)
    private String otrosGastosDetalle;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @PrePersist
    @SuppressWarnings("unused")
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }
    }

    @Override
    public CompraShowDTO asShowDTO() {
        CompraShowDTO compraShowDTO = new CompraShowDTO();
        compraShowDTO.setId(this.getId());
        compraShowDTO.setActivated(this.getActivated());
        compraShowDTO.setDistribuidor(this.getDistribuidor());
        compraShowDTO.setFecha(this.getFecha());
        compraShowDTO.setFlete(this.getFlete());
        compraShowDTO.setOtrosGastosCantidad(this.getOtrosGastosCantidad());
        compraShowDTO.setOtrosGastosDetalle(this.getOtrosGastosDetalle());
        compraShowDTO.setTotal(this.getTotal());
        return compraShowDTO;
    }
}
