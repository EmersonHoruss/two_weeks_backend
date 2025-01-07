package com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.product_provider;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;
import com.two_weeks_backend.two_weeks_backend.entities.purchase.ProductProvider;
import com.two_weeks_backend.two_weeks_backend.entities.purchase.Provider;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class ProductProviderCreateDTO extends BaseCreateDTO<ProductProvider> {
    @NotNull
    @Min(0)
    private float price;

    @NotNull
    private Long productId;

    @NonNull
    private Long providerId;

    @Override
    public ProductProvider asEntity() {
        ProductProvider provider = new ProductProvider();
        provider.setPrice(this.getPrice());
        provider.setProduct(this.getProductEntity());
        provider.setProvider(this.getProviderEntity());
        return provider;
    }

    private Product getProductEntity() {
        if (this.getProductId() != null) {
            Product product = new Product();
            product.setId(this.getProductId());
            return product;
        }
        return null;
    }

    private Provider getProviderEntity() {
        if (this.getProviderId() != null) {
            Provider provider = new Provider();
            provider.setId(this.getProviderId());
            return provider;
        }
        return null;
    }
}
