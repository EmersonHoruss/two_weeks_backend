package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.product.Product;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class ProductUpdateDTO extends BaseUpdateDTO<Product> {
    @NotNull
    @Min(0)
    private float sellPriceNormal;

    @NotNull
    @Min(0)
    private float sellPriceAuction;

    @NotNull
    @Min(0)
    private float sellPriceWholesale;

    @NotNull
    @Min(0)
    private float sellPriceWholesaleSuper;

    @Override
    public Product asEntity() {
        Product product = new Product();
        product.setId(this.getId());
        product.setSellPriceNormal(this.getSellPriceNormal());
        product.setSellPriceAuction(this.getSellPriceAuction());
        product.setSellPriceWholesale(this.getSellPriceWholesale());
        product.setSellPriceWholesaleSuper(this.getSellPriceWholesaleSuper());
        return product;
    }
}
