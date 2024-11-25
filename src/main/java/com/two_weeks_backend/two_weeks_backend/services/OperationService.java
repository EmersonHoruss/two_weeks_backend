package com.two_weeks_backend.two_weeks_backend.services;

import com.two_weeks_backend.two_weeks_backend.entities.*;
import com.two_weeks_backend.two_weeks_backend.repositories.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService extends BaseServiceImplementation<Operation>{
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public Operation create(Operation operation){
        Operation savedOperation = super.create(operation);
        savedOperation.setProduct(this.loadProduct(savedOperation.getProduct().getId()));
        return savedOperation;
    }

    private Product loadProduct(Long productId){
        return productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("product not found"));
    }
}