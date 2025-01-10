package com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.pay_method;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.PayMethod;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class PayMethodUpdateDTO extends BaseUpdateDTO<PayMethod> {
    @NotBlank
    private String name;

    @NotNull
    private boolean isVirtual;

    @Override
    public PayMethod asEntity() {
        PayMethod payMethod = new PayMethod();
        payMethod.setId(this.getId());
        payMethod.setName(this.getName());
        payMethod.setIsVirtual(this.isVirtual());
        return payMethod;
    }
}
