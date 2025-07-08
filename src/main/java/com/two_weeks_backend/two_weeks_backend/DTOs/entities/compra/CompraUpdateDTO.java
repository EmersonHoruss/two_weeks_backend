package com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_compra.DetalleCompraCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.CompraEntity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraUpdateDTO extends BaseUpdateDTO<CompraEntity>{
    @NotNull(message = "nombre del distribuidor es obligatorio")
    private String distribuidor;

    @NotNull(message = "la fecha es obligatoria")
    private OffsetDateTime fecha;

    @Min(value = 0, message = "el flete debe ser como mínimo 0")
    private BigDecimal flete;

    @Min(value = 0, message = "la cantidad de los otros gastos debe ser como mínimo 0")
    private BigDecimal otrosGastosCantidad;

    private String otrosGastosDetalle;

    @Min(value = 0, message = "el total debe ser como mínimo 0")
    private BigDecimal total;

    private List<DetalleCompraCreateDTO> detalles;
}
