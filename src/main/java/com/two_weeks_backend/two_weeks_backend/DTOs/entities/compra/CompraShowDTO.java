package com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_compra.DetalleCompraShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.CompraEntity;
import com.two_weeks_backend.two_weeks_backend.entities.DetalleCompraEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraShowDTO extends BaseShowDTO<CompraEntity> {
    private String distribuidorDuenio;
    private String distribuidorEmpresa;
    private OffsetDateTime fecha;
    private BigDecimal flete;
    private BigDecimal taxi;
    private BigDecimal otrosGastos;
    private BigDecimal total;
    private Boolean activated;
    private List<DetalleCompraShowDTO> detalles = new ArrayList<>();

    public void setDetalles(List<DetalleCompraEntity> detalles) {
        this.detalles = detalles.stream().map(detalle -> {
            return detalle.asShowDTO();
        }).toList();
    }
}