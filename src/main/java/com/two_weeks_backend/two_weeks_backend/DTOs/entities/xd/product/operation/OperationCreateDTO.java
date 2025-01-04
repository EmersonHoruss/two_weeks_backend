package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.operation;

import java.util.List;

import com.two_weeks_backend.two_weeks_backend.entities.product.Operation;
import com.two_weeks_backend.two_weeks_backend.entities.product.OperationType;
import com.two_weeks_backend.two_weeks_backend.entities.product.Product;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class OperationCreateDTO {
    @NotNull
    private OperationType type;

    @NotNull
    private Long productSetId;

    @NotNull
    @Size(min = 1)
    private List<@Size(min = 4, max = 4) String> productElementCodes;

    @NotBlank
    private String date;

    public Operation asEntity() {
        Operation operation = new Operation();
        operation.setType(this.getType());
        operation.setProductSet(this.getProductSetEntity());
        String codes = String.join(",", productElementCodes);
        operation.setProductElementCodes(codes);
        operation.setDate(getDate());
        return operation;
    }

    private Product getProductSetEntity() {
        if (this.getProductSetId() != null) {
            Product productSet = new Product();
            productSet.setId(this.getProductSetId());
            return productSet;
        }
        return null;
    }

    public int getAmount() {
        return this.productElementCodes.size();
    }
}
