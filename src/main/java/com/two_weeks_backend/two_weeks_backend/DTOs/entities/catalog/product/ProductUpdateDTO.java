package com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class ProductUpdateDTO extends BaseUpdateDTO<Product> {
    @Min(0)
    @NotNull
    private float sellPriceNormal;

    @Min(0)
    @NotNull
    private float sellPriceAuction;

    @Min(0)
    @NotNull
    private float sellPriceWholesale;

    @Min(0)
    @NotNull
    private float sellPriceWholesaleSuper;

    @Override
    public Product asEntity() {
        Product product = new Product();
        product.setSellPriceNormal(this.getSellPriceNormal());
        product.setSellPriceAuction(this.getSellPriceAuction());
        product.setSellPriceWholesale(this.getSellPriceWholesale());
        product.setSellPriceWholesaleSuper(this.getSellPriceWholesaleSuper());
        return product;
    }
}
