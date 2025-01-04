package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.brand.BrandShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.CompanyShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.size.SizeShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.type.TypeShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.product.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductShowDTO extends BaseShowDTO<Product> {
    private int stock;
    private float sellPriceNormal;
    private float sellPriceAuction;
    private float sellPriceWholesale;
    private float sellPriceWholesaleSuper;
    private String code;
    private boolean activated;
    private TypeShowDTO type;
    private BrandShowDTO brand;
    private SizeShowDTO size;
    private CompanyShowDTO company;
}
