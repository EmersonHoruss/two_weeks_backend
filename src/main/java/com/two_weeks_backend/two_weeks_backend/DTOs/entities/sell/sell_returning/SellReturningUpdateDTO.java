package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_returning;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellReturning;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellReturningUpdateDTO extends BaseUpdateDTO<SellReturning> {
    @NotBlank
    private String date;

    @Override
    public SellReturning asEntity() {
        SellReturning sellReturning = new SellReturning();
        sellReturning.setId(this.getId());
        sellReturning.setDate(this.getDate());
        return sellReturning;
    }
}
