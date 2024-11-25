package com.two_weeks_backend.two_weeks_backend.DTOs.entities.pointOfSale;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class PointOfSaleShowDTO extends BaseShowDTO<PointOfSale>{
    private LocalDate date;
    private float initialAmount;
    private float brutalEarning;
    private float virtualAmount;
    private float phisicalAmount;
}
