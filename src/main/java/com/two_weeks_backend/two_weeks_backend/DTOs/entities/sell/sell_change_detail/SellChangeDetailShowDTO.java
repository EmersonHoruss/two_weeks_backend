package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_change_detail;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.product.ProductShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellChangeDetail;

import lombok.*;

@Getter
@Setter
public class SellChangeDetailShowDTO extends BaseShowDTO<SellChangeDetail> {
    private int amount;
    private boolean activated;
    private ProductShowDTO changedProduct;
    private ProductShowDTO newProduct;
}
