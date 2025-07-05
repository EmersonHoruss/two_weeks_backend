package com.two_weeks_backend.two_weeks_backend.entities;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.caja.CajaShowDTO;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name="caja")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CajaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="fecha", nullable = false)
    private OffsetDateTime fecha;

    @Column(name = "monto_inicial", nullable=false)
    private BigDecimal montoInicial;

    @Column(name="monto_final_fisico", nullable=false)
    private BigDecimal montoFinalFisico;

    @Column(name="monto_final_digital", nullable=false)
    private BigDecimal montoFinalDigital;
    
    @Column(name="ganancia", nullable=false)
    private BigDecimal ganancia;

    public CajaShowDTO asShowDTO() {
        CajaShowDTO cajaShowDTO = new CajaShowDTO();
        cajaShowDTO.setId(this.getId());
        cajaShowDTO.setFecha(this.getFecha());
        cajaShowDTO.setMontoInicial(this.getMontoInicial());
        cajaShowDTO.setMontoFinalFisico(this.getMontoFinalFisico());
        cajaShowDTO.setMontoFinalDigital(this.getMontoFinalDigital());
        cajaShowDTO.setGanancia(this.getGanancia());
        return cajaShowDTO;
    }

    public void checkIfPossibleUpdating() {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        OffsetDateTime cajaFecha = this.fecha.withOffsetSameInstant(ZoneOffset.UTC);

        if (!cajaFecha.truncatedTo(ChronoUnit.DAYS).equals(now.truncatedTo(ChronoUnit.DAYS))) {
            throw new IllegalStateException("No se puede modificar una caja de otra fecha.");
        }
    }

    public void calculateGanancia() {
        BigDecimal montoFinal = this.montoFinalDigital.add(this.montoFinalFisico);
        this.ganancia = this.montoInicial.subtract(montoFinal);
    }
}