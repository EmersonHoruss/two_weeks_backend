package com.two_weeks_backend.two_weeks_backend.entities.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_pay_method.SellPayMethodShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.PayMethod;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sell_pay_method")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SellPayMethod extends BaseEntity<SellPayMethodShowDTO> {
    @Column(name = "amount", nullable = false)
    private float amount;

    @ManyToOne
    @JoinColumn(name = "pay_method", nullable = false, foreignKey = @ForeignKey(name = "fk_sell_pay_method_pay_method"))
    private PayMethod payMethod;

    @ManyToOne
    @JoinColumn(name = "sell", nullable = false, foreignKey = @ForeignKey(name = "fk_sell_pay_method_sell"))
    private Sell sell;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @PrePersist
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }
    }

    public SellPayMethodShowDTO asShowDTO() {
        SellPayMethodShowDTO sellPayMethodShowDTO = new SellPayMethodShowDTO();
        sellPayMethodShowDTO.setId(this.getId());
        sellPayMethodShowDTO.setActivated(this.getActivated());
        sellPayMethodShowDTO.setAmount(this.getAmount());
        sellPayMethodShowDTO.setPayMethod(this.getPayMethod().asShowDTO());
        return sellPayMethodShowDTO;
    }
}
