package com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.product_provider;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.purchase.ProductProvider;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class ProductProviderUpdateDTO extends BaseUpdateDTO<ProductProvider> {
    @NotNull
    @Min(0)
    private float price;

    @Override
    public ProductProvider asEntity() {
        ProductProvider provider = new ProductProvider();
        provider.setId(this.getId());
        provider.setPrice(this.getPrice());
        return provider;
    }
}
