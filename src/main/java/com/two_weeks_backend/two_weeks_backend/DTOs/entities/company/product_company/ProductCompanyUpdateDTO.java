package com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.product_company;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.company.ProductCompany;

import lombok.*;

@Getter
@Setter
public class ProductCompanyUpdateDTO extends BaseUpdateDTO<ProductCompany> {
    @Override
    public ProductCompany asEntity() {
        throw new UnsupportedOperationException("Producto de la empresa no tiene soporte a actualización por API");
    }
}
