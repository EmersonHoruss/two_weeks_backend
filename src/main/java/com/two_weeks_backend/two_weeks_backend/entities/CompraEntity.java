package com.two_weeks_backend.two_weeks_backend.entities;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraShowDTO;

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
@Table(name = "compra")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompraEntity extends BaseEntity<CompraShowDTO> {
    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;

    @Column(name = "flete", nullable = true)
    private BigDecimal flete;

    @Column(name = "taxi", nullable = true)
    private BigDecimal taxi;

    @Column(name = "otros_gastos", nullable = true)
    private BigDecimal otrosGastos;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "distribuidor", nullable = false, foreignKey = @ForeignKey(name = "fk_compra_distribuidor"))
    private DistribuidorEntity distribuidor;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @Column(name = "llego", columnDefinition = "boolean default false", nullable = false)
    private Boolean llego;

    @Column(name = "llego_fecha", nullable = true)
    private OffsetDateTime llegoFecha;

    @PrePersist
    @SuppressWarnings("unused")
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }
        if (this.llego == null) {
            this.llego = false;
        }
    }

    @Override
    public CompraShowDTO asShowDTO() {
        CompraShowDTO compraShowDTO = new CompraShowDTO();
        compraShowDTO.setId(this.getId());
        compraShowDTO.setActivated(this.getActivated());
        compraShowDTO.setDistribuidorDuenio(this.getDistribuidor().getDuenio());
        compraShowDTO.setDistribuidorEmpresa(this.getDistribuidor().getEmpresaNombre());
        compraShowDTO.setFecha(this.getFecha());
        compraShowDTO.setFlete(this.getFlete());
        compraShowDTO.setTaxi(this.getTaxi());
        compraShowDTO.setOtrosGastos(this.getOtrosGastos());
        compraShowDTO.setTotal(this.getTotal());
        compraShowDTO.setLlego(this.getLlego());
        compraShowDTO.setLlegoFecha(this.getLlegoFecha());
        return compraShowDTO;
    }
}
