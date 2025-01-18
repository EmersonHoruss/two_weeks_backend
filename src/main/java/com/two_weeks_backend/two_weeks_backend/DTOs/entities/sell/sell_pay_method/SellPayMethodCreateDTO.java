package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_pay_method;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellPayMethod;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.PayMethod;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellPayMethodCreateDTO extends BaseCreateDTO<SellPayMethod> {
    @NotNull
    @Min(1)
    private float amount;

    @NotNull
    private Long sellId;

    @NotNull
    private Long payMethodId;

    public SellPayMethod asEntity() {
        SellPayMethod sellPayMethod = new SellPayMethod();
        sellPayMethod.setAmount(this.getAmount());
        sellPayMethod.setSell(this.getSellEntity());
        sellPayMethod.setPayMethod(this.getPayMethodEntity());
        return sellPayMethod;
    }

    private Sell getSellEntity() {
        if (this.getSellId() != null) {
            Sell sell = new Sell();
            sell.setId(this.getSellId());
            return sell;
        }
        return null;
    }

    private PayMethod getPayMethodEntity() {
        if (this.getPayMethodId() != null) {
            PayMethod payMethod = new PayMethod();
            payMethod.setId(this.getPayMethodId());
            return payMethod;
        }
        return null;
    }
}
