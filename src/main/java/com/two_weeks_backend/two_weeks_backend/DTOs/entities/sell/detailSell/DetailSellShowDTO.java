package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.detailSell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.product.ProductShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell.SellShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.DetailSell;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailSellShowDTO extends BaseShowDTO<DetailSell>{
    private float price;
    private short amount;
    private float totalPrice;
    private SellShowDTO sell;
    private ProductShowDTO product;
    private boolean activated;
}
