package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_returning;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellReturning;

import lombok.*;

@Getter
@Setter
public class SellReturningShowDTO extends BaseShowDTO<SellReturning> {
    private String date;
    private boolean activated;
}
