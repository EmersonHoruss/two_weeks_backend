package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_pay_method;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellPayMethod;

import lombok.*;

@Getter
@Setter
public class SellPayMethodActivatedDTO extends BaseActivatedDTO<SellPayMethod> {
    @Override
    public SellPayMethod asEntity() {
        SellPayMethod sellPayMethod = new SellPayMethod();
        sellPayMethod.setId(this.getId());
        sellPayMethod.setActivated(this.getActivated());
        return sellPayMethod;
    }
}
