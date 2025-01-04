package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.operation;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.product.ProductSetShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.product.Operation;
import com.two_weeks_backend.two_weeks_backend.entities.product.OperationType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationShowDTO extends BaseShowDTO<Operation> {
    private OperationType type;
    private ProductSetShowDTO productSet;
    private String productElementCodes;
    private String date;
}
