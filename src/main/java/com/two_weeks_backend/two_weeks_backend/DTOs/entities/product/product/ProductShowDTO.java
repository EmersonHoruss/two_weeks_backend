package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.brand.BrandShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.size.SizeShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.type.TypeShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.product.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductShowDTO extends BaseShowDTO<Product>{
    private short stock;
    private float purchasePrice;
    private float sellPriceNormal;
    private float sellPriceWholesale1;
    private float sellPriceWholesale2;
    private String code;
    private boolean activated;
    private TypeShowDTO type;
    private BrandShowDTO brand;
    private SizeShowDTO size;
}
