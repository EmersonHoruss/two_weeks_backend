package com.two_weeks_backend.two_weeks_backend.DTOs.entities.point_of_sale.point_of_sale;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.point_of_sale.PointOfSale;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointOfSaleActivatedDTO extends BaseActivatedDTO<PointOfSale> {
    @Override
    public PointOfSale asEntity() {
        PointOfSale pointOfSale = new PointOfSale();
        pointOfSale.setId(this.getId());
        return pointOfSale;
    }
}
