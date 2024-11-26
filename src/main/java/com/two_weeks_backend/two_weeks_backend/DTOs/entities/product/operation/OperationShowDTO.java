package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.operation;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.product.ProductShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.product.Operation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationShowDTO extends BaseShowDTO<Operation>{
    private String type;
    private short amount;
    private String date;
    private ProductShowDTO product;
}
