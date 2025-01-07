package com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.product_company;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.product.ProductShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.company.ProductCompany;

import lombok.*;

@Getter
@Setter
public class ProductCompanyShowDTO extends BaseShowDTO<ProductCompany> {
    private int stock;
    private String code;
    private ProductShowDTO product;
    private boolean activated;
}
