package com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private BigDecimal total;

    public void setAllCalculatedData() {
        this.calculateTotal();
    }

    private void calculateTotal() {
        if (this.detalles == null || this.detalles.isEmpty()) {
            throw new RuntimeException("Debe haber al menos un producto para vender");
        }

        boolean hasAtLeastOneActivated = this.detalles.stream().anyMatch(detalle -> Boolean.TRUE.equals(detalle.getActi))

        BigDecimal detallesSubTotal = this.getDetalles().stream().map(DetalleCompraUpdateDTO::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.total = detallesSubTotal.add(flete).add(taxi).add(otrosGastos);
    }

    @Override
    public CompraEntity asEntity() {
        CompraEntity compra = new CompraEntity();
        compra.setFecha(this.getFecha());
        compra.setFlete(this.getFlete());
        compra.setTaxi(this.getTaxi());
        compra.setOtrosGastos(this.getOtrosGastos());

        if (this.getTotal() == null || this.getTotal().compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("El total no puede ser cero.");
        }
        compra.setTotal(this.getTotal());

        DistribuidorEntity distribuidor = new DistribuidorEntity();
        distribuidor.setId(this.getDistribuidorId());
        compra.setDistribuidor(distribuidor);

        return compra;
    }
}
