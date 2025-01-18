package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_returning_detail;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellReturningDetail;

import lombok.*;

@Getter
@Setter
public class SellReturningDetailActivatedDTO extends BaseActivatedDTO<SellReturningDetail> {
    @Override
    public SellReturningDetail asEntity() {
        SellReturningDetail sellReturningDetail = new SellReturningDetail();
        sellReturningDetail.setId(this.getId());
        sellReturningDetail.setActivated(this.getActivated());
        return sellReturningDetail;
    }
}
