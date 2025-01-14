package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.product.ProductShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.PriceType;

import lombok.*;

@Getter
@Setter
public class SellDetailShowDTO {
    private Long id;
    private boolean activated;
    private int amount;
    private PriceType priceType;
    private float price;
    private float subTotal;
    private boolean isReturned;
    private ProductShowDTO product;
    private Long changedFrom;
}
