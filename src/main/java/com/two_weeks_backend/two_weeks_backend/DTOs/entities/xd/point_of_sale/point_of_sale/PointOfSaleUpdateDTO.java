package com.two_weeks_backend.two_weeks_backend.DTOs.entities.point_of_sale.point_of_sale;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.point_of_sale.PointOfSale;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class PointOfSaleUpdateDTO extends BaseUpdateDTO<PointOfSale>{
    @NotNull
    private LocalDate date;
    @NotNull
    @Min(0)
    private float initialAmount;
    @Min(0)
    private float brutalEarning;
    @Min(0)
    private float virtualAmount;
    @Min(0)
    private float phisicalAmount;

    @Override
    public PointOfSale asEntity() {
        PointOfSale pointOfSale = new PointOfSale();
        pointOfSale.setId(this.getId());
        pointOfSale.setDate(this.getDate());
        pointOfSale.setInitialAmount(this.getInitialAmount());
        pointOfSale.setBrutalEarning(this.getBrutalEarning());
        pointOfSale.setVirtualAmount(this.getVirtualAmount());
        pointOfSale.setPhisicalAmount(this.getPhisicalAmount());
        return pointOfSale;
    }

}
