package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_pay_method;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellPayMethodCreateDTO {
    @NotNull
    @Min(1)
    private float amount;

    @NotNull
    private Long payMethodId;
}
