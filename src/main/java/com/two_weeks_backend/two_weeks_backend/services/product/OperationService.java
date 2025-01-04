package com.two_weeks_backend.two_weeks_backend.services.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.operation.OperationCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.product.Operation;
import com.two_weeks_backend.two_weeks_backend.entities.product.OperationType;
import com.two_weeks_backend.two_weeks_backend.entities.product.ProductSet;
import com.two_weeks_backend.two_weeks_backend.exceptions.UnableToExecute;
import com.two_weeks_backend.two_weeks_backend.repositories.product.ProductSetRepository;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService extends BaseServiceImplementation<Operation> {
    @Autowired
    private ProductSetRepository productSetRepository;

    @Transactional(rollbackFor = Exception.class)
    public Operation create(OperationCreateDTO operation) {
        Long productSetId = operation.getProductSetId();
        ProductSet retrievedProductSet = this.loadProductSet(productSetId);
        if (!retrievedProductSet.getActivated()) {
            throw new UnableToExecute("Operación fallida: El producto está eliminado");
        }

        int stock = retrievedProductSet.getStock();
        int amount = operation.getAmount();

        OperationType operationType = operation.getType();
        boolean isSubtract = operationType == OperationType.SUBTRACT;

        if (isSubtract && stock < amount) {
            throw new UnableToExecute(
                    "Operación fallida: Sustracción de productos debe ser menor o igual al stock (" + stock + ")");
        }

        int newStock = stock;
        if (isSubtract) {
            newStock -= amount;
        }

        boolean isAdd = operationType == OperationType.ADD;
        if (isAdd) {
            newStock += amount;
        }

        Operation savedOperation = super.create(operation.asEntity());

        retrievedProductSet.setStock(newStock);
        this.productSetRepository.save(retrievedProductSet);

        savedOperation.setProductSet(retrievedProductSet);
        return savedOperation;
    }

    private ProductSet loadProductSet(Long productSetId) {
        return productSetRepository.findById(productSetId)
                .orElseThrow(() -> new RuntimeException("product not found"));
    }
}
