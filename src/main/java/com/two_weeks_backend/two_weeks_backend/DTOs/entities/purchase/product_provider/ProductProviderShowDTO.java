package com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.product_provider;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.product.ProductShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.purchase.ProductProvider;

import lombok.*;

@Getter
@Setter
public class ProductProviderShowDTO extends BaseShowDTO<ProductProvider> {
    private float price;
    private boolean activated;
    private ProductShowDTO product;
}
