package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_pay_method;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.pay_method.PayMethodShowDTO;

import lombok.*;

@Getter
@Setter
public class SellPayMethodShowDTO {
    private Long id;
    private boolean activated;
    private String date;
    private float amount;
    private PayMethodShowDTO payMethod;
}
