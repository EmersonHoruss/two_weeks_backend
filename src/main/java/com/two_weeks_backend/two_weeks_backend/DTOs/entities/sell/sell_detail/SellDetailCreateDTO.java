package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail;

import com.two_weeks_backend.two_weeks_backend.entities.catalog.PriceType;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellDetail;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellDetailCreateDTO {
    @NotNull
    @Min(1)
    private int amount;

    @NotNull
    private PriceType priceType;

    @NotNull
    @Min(1)
    private float price;

    @NotNull
    private Long productId;

    public float getSubTotal() {
        return this.getAmount() * this.getPrice();
    }

    public SellDetail asEntity() {
        SellDetail sellDetail = new SellDetail();
        sellDetail.setAmount(this.getAmount());
        sellDetail.setPriceType(this.getPriceType());
        sellDetail.setPrice(this.getPrice());
        sellDetail.setSubTotal(this.getSubTotal());
        sellDetail.setIsReturned(false);
        sellDetail.setProduct(this.getProductEntity());
        return sellDetail;
    }

    private Product getProductEntity() {
        if (this.getProductId() != null) {
            Product product = new Product();
            product.setId(this.getProductId());
            return product;
        }
        return null;
    }
}
