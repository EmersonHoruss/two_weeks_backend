package com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.brand.BrandShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.size.SizeShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.type.TypeShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;

import lombok.*;

@Getter
@Setter
public class ProductShowDTO extends BaseShowDTO<Product> {
    private String code;
    private float sellPriceNormal;
    private float sellPriceAuction;
    private float sellPriceWholesale;
    private float sellPriceWholesaleSuper;
    private TypeShowDTO type;
    private BrandShowDTO brand;
    private SizeShowDTO size;
    private boolean activated;
}
