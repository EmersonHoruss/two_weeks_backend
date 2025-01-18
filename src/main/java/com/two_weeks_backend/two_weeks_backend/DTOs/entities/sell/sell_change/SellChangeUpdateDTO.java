package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_change;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellChange;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellChangeUpdateDTO extends BaseUpdateDTO<SellChange> {
    @NotBlank
    private String date;

    @Override
    public SellChange asEntity() {
        SellChange sellChange = new SellChange();
        sellChange.setId(this.getId());
        sellChange.setDate(this.getDate());
        return sellChange;
    }
}
