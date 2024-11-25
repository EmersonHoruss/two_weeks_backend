package com.two_weeks_backend.two_weeks_backend.services;

import com.two_weeks_backend.two_weeks_backend.entities.*;
import com.two_weeks_backend.two_weeks_backend.repositories.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
