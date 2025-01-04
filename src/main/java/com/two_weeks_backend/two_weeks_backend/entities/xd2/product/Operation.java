package com.two_weeks_backend.two_weeks_backend.entities.product;

import java.util.Arrays;
import java.util.List;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.operation.OperationShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "operation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Operation extends BaseEntity<OperationShowDTO> {
    @Column(nullable = false)
    private OperationType type;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_operation_product"))
    private Product product;

    @Column(nullable = false)
    private int amount;

    @Column(length = 255, nullable = false)
    private String date;

    public OperationShowDTO asShowDTO() {
        OperationShowDTO operationShowDTO = new OperationShowDTO();
        operationShowDTO.setId(this.getId());
        operationShowDTO.setType(this.getType());
        operationShowDTO.setProductSet(this.getProductSet().asShowDTO());
        operationShowDTO.setProductElementCodes(this.getProductElementCodes());
        operationShowDTO.setDate(this.getDate());
        return operationShowDTO;
    }

    public int getAmount() {
        List<String> productElementCodesList = Arrays.asList(this.getProductElementCodes().split(","));
        return productElementCodesList.size();
    }
}
