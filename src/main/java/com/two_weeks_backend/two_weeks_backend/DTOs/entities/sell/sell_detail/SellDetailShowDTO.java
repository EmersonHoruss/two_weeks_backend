package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.product.ProductShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.PriceType;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellDetail;

import lombok.*;

@Getter
@Setter
public class SellDetailShowDTO extends BaseShowDTO<SellDetail>{
    private int amount;
    private PriceType priceType;
    private float price;
    private float subTotal;
    private ProductShowDTO product;
    private boolean activated;
}
