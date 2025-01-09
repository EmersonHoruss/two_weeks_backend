package com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.operation;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.company.Operation;

import lombok.*;

@Getter
@Setter
public class OperationUpdateDTO extends BaseUpdateDTO<Operation> {
    @Override
    public Operation asEntity() {
        throw new UnsupportedOperationException("Operación no soporta actualización");
    }
}
