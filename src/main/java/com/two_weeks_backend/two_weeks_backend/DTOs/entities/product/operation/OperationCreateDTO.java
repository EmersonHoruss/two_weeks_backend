package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.operation;

import com.two_weeks_backend.two_weeks_backend.entities.product.Operation;
import com.two_weeks_backend.two_weeks_backend.entities.product.Product;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class OperationCreateDTO {
    @NotNull
    private String type;
    @NotNull
    @Min(1)
    private short amount;
    @NotNull
    private String date;
    @NotNull
    private Long productId;

    public Operation asEntity() {
        Operation operation = new Operation();
        operation.setType(this.getType());
        operation.setAmount(this.getAmount());
        operation.setDate(this.getDate());
        operation.setProduct(this.getProductEntity());
        return operation;
    }

    private Product getProductEntity() {
        if (this.getProductId() != null) {
            Product product = new Product();
            product.setId(this.getProductId());
            return product;
        }
        return null;
    }
}
