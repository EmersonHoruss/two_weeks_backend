package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_change;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellChange;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellChangeCreateDTO extends BaseCreateDTO<SellChange> {
    @NotBlank
    private String date;

    @NotNull
    private Long sellId;

    @Override
    public SellChange asEntity() {
        SellChange sellChange = new SellChange();
        String date = this.getDate().trim();
        sellChange.setDate(date);
        sellChange.setSell(this.getSellEntity());
        return sellChange;
    }

    private Sell getSellEntity() {
        if (this.getSellId() != null) {
            Sell sell = new Sell();
            sell.setId(this.getSellId());
            return sell;
        }
        return null;
    }
}
