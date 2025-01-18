package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_returning_detail;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellReturningDetail;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellReturningDetailUpdateDTO extends BaseUpdateDTO<SellReturningDetail> {
    @NotNull
    @Min(1)
    private int amount;

    @Override
    public SellReturningDetail asEntity() {
        SellReturningDetail sellReturningDetail = new SellReturningDetail();
        sellReturningDetail.setId(this.getId());
        sellReturningDetail.setAmount(this.getAmount());
        return sellReturningDetail;
    }
}
