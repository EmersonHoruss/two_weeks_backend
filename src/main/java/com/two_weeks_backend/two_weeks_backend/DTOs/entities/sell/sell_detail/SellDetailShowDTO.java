package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.product.ProductShowDTO;

import lombok.*;

@Getter
@Setter
public class SellDetailShowDTO {
    private Long id;
    private boolean activated;
    private String date;
    private int amount;
    private boolean isLowerPrice;
    private float price;
    private float subTotal;
    private ProductShowDTO product;
}
