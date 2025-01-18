package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_returning_detail;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.product.ProductShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellReturningDetail;

import lombok.*;

@Getter
@Setter
public class SellReturningDetailShowDTO extends BaseShowDTO<SellReturningDetail> {
    private int amount;
    private ProductShowDTO product;
}
