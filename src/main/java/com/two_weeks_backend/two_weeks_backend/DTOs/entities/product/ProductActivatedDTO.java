package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductActivatedDTO extends BaseActivatedDTO<Product> {
    @Override
    public Product asEntity() {
        Product product = new Product();
        product.setId(this.getId());
        product.setActivated(this.getActivated());
        return product;
    }
}
