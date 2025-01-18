package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_pay_method;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.pay_method.PayMethodShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellPayMethod;

import lombok.*;

@Getter
@Setter
public class SellPayMethodShowDTO extends BaseShowDTO<SellPayMethod> {
    private float amount;
    private PayMethodShowDTO payMethod;
    private boolean activated;
}
