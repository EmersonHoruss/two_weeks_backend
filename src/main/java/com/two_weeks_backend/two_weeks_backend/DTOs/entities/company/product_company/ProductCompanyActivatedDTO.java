package com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.product_company;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.company.ProductCompany;

import lombok.*;

@Getter
@Setter
public class ProductCompanyActivatedDTO extends BaseActivatedDTO<ProductCompany> {
    @Override
    public ProductCompany asEntity() {
        ProductCompany productCompany = new ProductCompany();
        productCompany.setId(this.getId());
        productCompany.setActivated(this.getActivated());
        return productCompany;
    }
}
