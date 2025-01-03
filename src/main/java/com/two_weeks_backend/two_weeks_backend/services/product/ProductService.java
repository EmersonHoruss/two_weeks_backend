package com.two_weeks_backend.two_weeks_backend.services.product;

import com.two_weeks_backend.two_weeks_backend.entities.product.Product;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseServiceImplementation<Product> {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Product create(Product product) {
        Product savedProduct = super.create(product);
        return savedProduct;
    }
}
