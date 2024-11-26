package com.two_weeks_backend.two_weeks_backend.entities.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.operation.OperationShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@SuppressWarnings("rawtypes")
@Entity
@Table(name = "operation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Operation extends BaseEntity {
    @Column(length = 255, nullable = false)
    private String type;
    @Column(nullable = false)
    private short amount;
    @Column(length = 255, nullable = false)
    private String date;
    @ManyToOne(optional = false)
    private Product product;

    @Override
    public OperationShowDTO asShowDTO(){
        OperationShowDTO operationShowDTO = new OperationShowDTO();
        operationShowDTO.setId(this.getId());
        operationShowDTO.setActivated(this.getActivated());
        operationShowDTO.setType(this.getType());
        operationShowDTO.setAmount(this.getAmount());
        operationShowDTO.setDate(this.getDate());
        operationShowDTO.setProduct(this.getProduct().asShowDTO());
        return operationShowDTO;
    }
}
