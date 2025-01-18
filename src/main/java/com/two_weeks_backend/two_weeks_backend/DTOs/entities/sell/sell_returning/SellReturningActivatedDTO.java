package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_returning;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellReturning;

import lombok.*;

@Getter
@Setter
public class SellReturningActivatedDTO extends BaseActivatedDTO<SellReturning> {
    @Override
    public SellReturning asEntity() {
        SellReturning sellReturning = new SellReturning();
        sellReturning.setId(this.getId());
        sellReturning.setActivated(this.getActivated());
        return sellReturning;
    }
}
