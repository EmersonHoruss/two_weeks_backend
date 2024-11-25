package com.two_weeks_backend.two_weeks_backend.DTOs.entities.operation;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.ProductShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class OperationShowDTO extends BaseShowDTO<Operation>{
    private String type;
    private short amount;
    private String date;
    private ProductShowDTO product;
}
