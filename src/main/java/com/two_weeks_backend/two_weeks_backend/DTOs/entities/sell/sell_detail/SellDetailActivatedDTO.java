package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellDetail;

import lombok.*;

@Getter
@Setter
public class SellDetailActivatedDTO extends BaseActivatedDTO<SellDetail> {
    @Override
    public SellDetail asEntity() {
        SellDetail sellDetail = new SellDetail();
        sellDetail.setId(this.getId());
        sellDetail.setActivated(this.getActivated());
        return sellDetail;
    }
}
