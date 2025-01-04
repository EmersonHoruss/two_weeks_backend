package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.product.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductActivatedDTO extends BaseActivatedDTO<Product> {
    @Override
    public Product asEntity() {
        Product productSet = new Product();
        productSet.setId(this.getId());
        productSet.setActivated(this.getActivated());
        return productSet;
    }
}
