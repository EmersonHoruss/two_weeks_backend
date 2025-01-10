package com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.pay_method;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.PayMethod;

import lombok.*;

@Getter
@Setter
public class PayMethodActivatedDTO extends BaseActivatedDTO<PayMethod> {
    @Override
    public PayMethod asEntity() {
        PayMethod payMethod = new PayMethod();
        payMethod.setId(this.getId());
        payMethod.setActivated(this.getActivated());
        return payMethod;
    }
}
