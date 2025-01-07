package com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.product_provider;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.purchase.ProductProvider;

import lombok.*;

@Getter
@Setter
public class ProductProviderActivatedDTO extends BaseActivatedDTO<ProductProvider> {
    @Override
    public ProductProvider asEntity() {
        ProductProvider productProvider = new ProductProvider();
        productProvider.setId(this.getId());
        productProvider.setActivated(this.getActivated());
        return productProvider;
    }
}
