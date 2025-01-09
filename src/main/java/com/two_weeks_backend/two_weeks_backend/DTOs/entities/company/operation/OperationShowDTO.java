package com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.operation;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.product.ProductShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.company.Operation;
import com.two_weeks_backend.two_weeks_backend.entities.company.OperationType;

import lombok.*;

@Getter
@Setter
public class OperationShowDTO extends BaseShowDTO<Operation> {
    private OperationType type;
    private int amount;
    private String date;
    private ProductShowDTO product;
}
