package com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.pay_method;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.PayMethod;

import lombok.*;

@Getter
@Setter
public class PayMethodShowDTO extends BaseShowDTO<PayMethod> {
    private String name;
    private boolean isVirtual;
    private boolean isJustForSell;
    private boolean activated;
}
