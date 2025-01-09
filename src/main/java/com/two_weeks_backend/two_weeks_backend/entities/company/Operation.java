package com.two_weeks_backend.two_weeks_backend.entities.company;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.operation.OperationShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;
import com.two_weeks_backend.two_weeks_backend.entities.user.User;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private OperationType type;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "date", length = 255, nullable = false)
    private String date;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false, foreignKey = @ForeignKey(name = "fk_operation_company"))
    private Company company;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_operation_product"))
    private Product product;

    @ManyToOne
    @JoinColumn(name = "operator_id", nullable = false, foreignKey = @ForeignKey(name = "fk_operation_operator"))
    private User operator;

    @Override
    public OperationShowDTO asShowDTO() {
        OperationShowDTO operationShowDTO = new OperationShowDTO();
        operationShowDTO.setId(this.getId());
        operationShowDTO.setType(this.getType());
        operationShowDTO.setAmount(this.getAmount());
        operationShowDTO.setDate(this.getDate());
        operationShowDTO.setProduct(this.getProduct().asShowDTO());
        return operationShowDTO;
    }
}
