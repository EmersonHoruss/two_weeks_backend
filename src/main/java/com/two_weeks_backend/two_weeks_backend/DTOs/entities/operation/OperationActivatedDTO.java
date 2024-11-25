package com.two_weeks_backend.two_weeks_backend.DTOs.entities.operation;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.Operation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationActivatedDTO extends BaseActivatedDTO<Operation> {
    @Override
    public Operation asEntity() {
        Operation operation = new Operation();
        operation.setId(this.getId());
        operation.setActivated(this.getActivated());
        return operation;
    }
}
