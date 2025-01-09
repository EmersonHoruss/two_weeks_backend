package com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.operation;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;
import com.two_weeks_backend.two_weeks_backend.entities.company.Company;
import com.two_weeks_backend.two_weeks_backend.entities.company.Operation;
import com.two_weeks_backend.two_weeks_backend.entities.company.OperationType;
import com.two_weeks_backend.two_weeks_backend.entities.user.User;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class OperationCreateDTO extends BaseCreateDTO<Operation> {
    @NotNull
    private OperationType type;

    @NotNull
    @Min(1)
    private int amount;

    @NotBlank
    private String date;

    @NotNull
    private Long companyId;

    @NotNull
    private Long productId;

    @NotNull
    private Long operatorId;

    @Override
    public Operation asEntity() {
        Operation operation = new Operation();
        operation.setType(this.getType());
        operation.setAmount(this.getAmount());
        operation.setDate(this.getDate());
        operation.setCompany(this.getCompanyEntity());
        operation.setProduct(this.getProductEntity());
        operation.setOperator(this.getOperatorEntity());
        return operation;
    }

    private Company getCompanyEntity() {
        if (this.getCompanyId() != null) {
            Company company = new Company();
            company.setId(this.getCompanyId());
            return company;
        }
        return null;
    }

    private Product getProductEntity() {
        if (this.getProductId() != null) {
            Product product = new Product();
            product.setId(this.getProductId());
            return product;
        }
        return null;
    }

    private User getOperatorEntity() {
        if (this.getOperatorId() != null) {
            User operator = new User();
            operator.setId(this.getOperatorId());
            return operator;
        }
        return null;
    }
}
