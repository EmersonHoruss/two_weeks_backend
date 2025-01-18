package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.PriceType;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellDetail;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellDetailCreateDTO extends BaseCreateDTO<SellDetail> {
    @NotNull
    @Min(1)
    private int amount;

    @NotNull
    private PriceType priceType;

    @NotNull
    @Min(1)
    private float price;

    @NotNull
    private Long sellId;

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
        sellDetail.setSell(this.getSellEntity());
        sellDetail.setProduct(this.getProductEntity());
        return sellDetail;
    }

    private Sell getSellEntity() {
        if (this.getSellId() != null) {
            Sell sell = new Sell();
            sell.setId(this.getSellId());
            return sell;
        }
        return null;
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
