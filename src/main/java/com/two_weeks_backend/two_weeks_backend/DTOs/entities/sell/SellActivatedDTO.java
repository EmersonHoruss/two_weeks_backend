package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.Sell;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellActivatedDTO extends BaseActivatedDTO<Sell> {
    @Override
    public Sell asEntity() {
        Sell sell = new Sell();
        sell.setId(this.getId());
        sell.setActivated(this.getActivated());
        return sell;
    }
}
