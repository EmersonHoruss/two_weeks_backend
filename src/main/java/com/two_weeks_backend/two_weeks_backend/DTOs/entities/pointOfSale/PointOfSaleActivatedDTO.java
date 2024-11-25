package com.two_weeks_backend.two_weeks_backend.DTOs.entities.pointOfSale;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.PointOfSale;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointOfSaleActivatedDTO extends BaseActivatedDTO<PointOfSale> {
    @Override
    public PointOfSale asEntity() {
        PointOfSale pointOfSale = new PointOfSale();
        pointOfSale.setId(this.getId());
        pointOfSale.setActivated(this.getActivated());
        return pointOfSale;
    }
}
