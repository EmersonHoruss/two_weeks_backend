package com.two_weeks_backend.two_weeks_backend.services.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.operation.OperationCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.product.Operation;
import com.two_weeks_backend.two_weeks_backend.entities.product.Product;
import com.two_weeks_backend.two_weeks_backend.exceptions.UnableToExecute;
import com.two_weeks_backend.two_weeks_backend.repositories.product.ProductRepository;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService extends BaseServiceImplementation<Operation> {
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Operation create(OperationCreateDTO operation) {
        Long productId = operation.getProductId();
        Product product = this.loadProduct(productId);
        if (!product.getActivated()) {
            throw new UnableToExecute("el producto está eliminado lógicamente");
        }

        short stock = product.getStock();
        short operationAmount = operation.getAmount();

        boolean isSubtract = operation.getType().equals("Quitar");

        if (isSubtract && stock < operationAmount) {
            throw new UnableToExecute(
                    "la substracción de productos debe ser menor o igual al stock.");
        }

        short newStock = stock;
        if (isSubtract) {
            newStock -= operationAmount;
        }

        boolean isAdd = operation.getType().equals("Agregar");
        if (isAdd) {
            newStock += operationAmount;
        }

        Product updatedProduct = new Product();
        updatedProduct.setId(productId);
        updatedProduct.setStock(newStock);

        productRepository.save(updatedProduct);

        Operation savedOperation = super.create(operation.asEntity());
        savedOperation.setProduct(this.loadProduct(savedOperation.getProduct().getId()));
        return savedOperation;
    }

    private Product loadProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("product not found"));
    }
}
