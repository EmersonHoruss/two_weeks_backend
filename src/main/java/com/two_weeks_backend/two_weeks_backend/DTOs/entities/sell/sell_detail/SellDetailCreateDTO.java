package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellDetailCreateDTO {
    @NotNull
    @Min(1)
    private int amount;

    @NotNull
    private boolean isOutOfPrice;

    @NotNull
    @Min(1)
    private float price;

    @NotNull
    private Long productId;

    public float getSubTotal() {
        return this.getAmount() * this.getPrice();
    }
}
