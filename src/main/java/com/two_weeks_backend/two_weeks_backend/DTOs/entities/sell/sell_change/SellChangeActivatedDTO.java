package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_change;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellChange;

import lombok.*;

@Getter
@Setter
public class SellChangeActivatedDTO extends BaseActivatedDTO<SellChange> {
    @Override
    public SellChange asEntity() {
        SellChange sellChange = new SellChange();
        sellChange.setId(this.getId());
        sellChange.setActivated(this.getActivated());
        return sellChange;
    }
}
