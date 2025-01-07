package com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.product_company;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;
import com.two_weeks_backend.two_weeks_backend.entities.company.Company;
import com.two_weeks_backend.two_weeks_backend.entities.company.ProductCompany;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class ProductCompanyCreateDTO extends BaseCreateDTO<ProductCompany> {
    @Min(0)
    @NotNull
    private int stock;

    @NotNull
    private Long productId;

    @NotNull
    private Long companyId;

    @Override
    public ProductCompany asEntity() {
        ProductCompany productCompany = new ProductCompany();
        productCompany.setStock(this.getStock());
        productCompany.setProduct(this.getProductEntity());
        productCompany.setCompany(this.getCompanyEntity());
        return productCompany;
    }

    private Product getProductEntity() {
        if (this.getProductId() != null) {
            Product product = new Product();
            product.setId(this.getProductId());
            return product;
        }
        return null;
    }

    private Company getCompanyEntity() {
        if (this.getCompanyId() != null) {
            Company company = new Company();
            company.setId(this.getCompanyId());
            return company;
        }
        return null;
    }
}
