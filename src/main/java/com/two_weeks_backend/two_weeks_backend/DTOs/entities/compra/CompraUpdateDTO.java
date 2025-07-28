package com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_compra.DetalleCompraUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.CompraEntity;
import com.two_weeks_backend.two_weeks_backend.entities.DistribuidorEntity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraUpdateDTO extends BaseUpdateDTO<CompraEntity> {
    @NotNull(message = "La fecha es obligatoria")
    private OffsetDateTime fecha;

    @Min(value = 0, message = "El flete debe ser como mínimo 0")
    private BigDecimal flete = BigDecimal.ZERO;

    @Min(value = 0, message = "El pasaje del taxi debe ser como mínimo 0")
    private BigDecimal taxi = BigDecimal.ZERO;

    @Min(value = 0, message = "La cantidad de los otros gastos debe ser como mínimo 0")
    private BigDecimal otrosGastos = BigDecimal.ZERO;

    @NotNull(message = "Debe haber al menos un producto para vender")
    @NotEmpty(message = "Debe haber al menos un producto para vender")
    private List<DetalleCompraUpdateDTO> detalles;

    @NotNull(message = "El distribuidor es obligatorio")
    private Long distribuidorId;

    @Override
    public CompraEntity asEntity() {
        CompraEntity compra = new CompraEntity();
        compra.setFecha(this.getFecha());
        compra.setFlete(this.getFlete());
        compra.setTaxi(this.getTaxi());
        compra.setOtrosGastos(this.getOtrosGastos());

        DistribuidorEntity distribuidor = new DistribuidorEntity();
        distribuidor.setId(this.getDistribuidorId());
        compra.setDistribuidor(distribuidor);

        return compra;
    }
}
