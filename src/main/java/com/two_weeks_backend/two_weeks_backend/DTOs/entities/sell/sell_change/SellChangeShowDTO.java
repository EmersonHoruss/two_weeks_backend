package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_change;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellChange;

import lombok.*;

@Getter
@Setter
public class SellChangeShowDTO extends BaseShowDTO<SellChange> {
    private String date;
    private boolean activated;
}
