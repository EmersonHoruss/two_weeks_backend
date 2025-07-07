package com.two_weeks_backend.two_weeks_backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.venta.VentaShowDTO;

@Entity
@Table(name = "venta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VentaEntity extends BaseEntity<VentaShowDTO> {
    @Column(name = "comprador", nullable = false)
    private String comprador;

    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;

    @Column(name = "monto_pagado_digital", nullable = false)
    private BigDecimal montoPagadoDigital;

    @Column(name = "monto_pagado_fisico", nullable = false)
    private BigDecimal montoPagadoFisico;

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
    public VentaShowDTO asShowDTO() {
        VentaShowDTO ventaShowDTO = new VentaShowDTO();
        ventaShowDTO.setId(this.getId());
        ventaShowDTO.setActivated(this.getActivated());
        ventaShowDTO.setComprador(this.getComprador());
        ventaShowDTO.setFecha(this.getFecha());
        ventaShowDTO.setMontoPagadoDigital(this.getMontoPagadoDigital());
        ventaShowDTO.setMontoPagadoFisico(this.getMontoPagadoFisico());
        ventaShowDTO.setTotal(this.getTotal());
        return ventaShowDTO;
    }
}
