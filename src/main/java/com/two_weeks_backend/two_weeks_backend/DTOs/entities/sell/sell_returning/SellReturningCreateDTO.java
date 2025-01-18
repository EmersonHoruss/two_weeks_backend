package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_returning;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellReturning;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellReturningCreateDTO extends BaseCreateDTO<SellReturning> {
    @NotBlank
    private String date;

    @NotNull
    private Long sellId;

    @Override
    public SellReturning asEntity() {
        SellReturning sellReturning = new SellReturning();
        String date = this.getDate().trim();
        sellReturning.setDate(date);
        sellReturning.setSell(this.getSellEntity());
        return sellReturning;
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
