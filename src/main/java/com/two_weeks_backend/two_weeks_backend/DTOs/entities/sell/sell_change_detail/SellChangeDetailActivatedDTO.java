package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_change_detail;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellChangeDetail;

import lombok.*;

@Getter
@Setter
public class SellChangeDetailActivatedDTO extends BaseActivatedDTO<SellChangeDetail> {
    @Override
    public SellChangeDetail asEntity() {
        SellChangeDetail sellChangeDetail = new SellChangeDetail();
        sellChangeDetail.setId(this.getId());
        sellChangeDetail.setActivated(this.getActivated());
        return sellChangeDetail;
    }
}
