package com.two_weeks_backend.two_weeks_backend.entities.product;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String type;
    @Column(nullable = false)
    private short amount;
    @Column(length = 255, nullable = false)
    private String date;
    @ManyToOne(optional = false)
    private Product product;

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
