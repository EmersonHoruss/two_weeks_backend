package com.two_weeks_backend.two_weeks_backend.services;

import com.two_weeks_backend.two_weeks_backend.entities.*;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseServiceImplementation<Product>{

    @Override
    @Transactional
    public Product create(Product product){
        Product savedProduct = super.create(product);
        return savedProduct;
    }

}
