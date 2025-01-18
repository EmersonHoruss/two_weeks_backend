package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_pay_method;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellPayMethod;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellPayMethodUpdateDTO extends BaseUpdateDTO<SellPayMethod> {
    @NotNull
    @Min(1)
    private float amount;

    public SellPayMethod asEntity() {
        SellPayMethod sellPayMethod = new SellPayMethod();
        sellPayMethod.setId(this.getId());
        sellPayMethod.setAmount(this.getAmount());
        return sellPayMethod;
    }
}
