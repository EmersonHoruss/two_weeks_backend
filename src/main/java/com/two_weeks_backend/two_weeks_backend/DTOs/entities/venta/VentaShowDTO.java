package com.two_weeks_backend.two_weeks_backend.DTOs.entities.venta;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_venta.DetalleVentaShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.DetalleVentaEntity;
import com.two_weeks_backend.two_weeks_backend.entities.VentaEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaShowDTO extends BaseShowDTO<VentaEntity> {
    private String comprador;
    private OffsetDateTime fecha;
    private BigDecimal montoPagadoDigital;
    private BigDecimal montoPagadoFisico;
    private BigDecimal total;
    private Boolean activated;
    private List<DetalleVentaShowDTO> detalles = new ArrayList<>();

    public void setDetalles(List<DetalleVentaEntity> detalles) {
        this.detalles = detalles.stream().map(detalle -> {
            return detalle.asShowDTO();
        }).toList();
    }
}
