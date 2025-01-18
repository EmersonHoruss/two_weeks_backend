package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_change_detail;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellChangeDetail;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellChangeDetailUpdateDTO extends BaseUpdateDTO<SellChangeDetail> {
    @NotNull
    @Min(1)
    private int amount;

    @Override
    public SellChangeDetail asEntity() {
        SellChangeDetail sellChangeDetail = new SellChangeDetail();
        sellChangeDetail.setId(this.getId());
        sellChangeDetail.setAmount(this.getAmount());
        return sellChangeDetail;
    }
}
