package com.two_weeks_backend.two_weeks_backend.entities.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell.SellShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.user.User;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@SuppressWarnings("rawtypes")
@Entity
@Table(name = "sell")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Sell extends BaseEntity {
    @Column()
    private float totalAmount;
    @Column()
    private LocalDate date;
    @Column(length = 255)
    private String paymentMethod;
    @Column(length = 255)
    private String status;
    @ManyToOne(optional = false)
    private User seller;
    @ManyToOne
    private User debtCollector;
    @ManyToOne
    private Customer customer;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @PrePersist
    private void prePersist(){
        if(activated == null){
            activated = true;
        }
    }

    @Override
    public SellShowDTO asShowDTO(){
        SellShowDTO sellShowDTO = new SellShowDTO();
        sellShowDTO.setId(this.getId());
        sellShowDTO.setActivated(this.getActivated());
        sellShowDTO.setTotalAmount(this.getTotalAmount());
        sellShowDTO.setDate(this.getDate());
        sellShowDTO.setPaymentMethod(this.getPaymentMethod());
        sellShowDTO.setStatus(this.getStatus());
        sellShowDTO.setSeller(this.getSeller().asShowDTO());
        sellShowDTO.setDebtCollector(this.getDebtCollector().asShowDTO());
        sellShowDTO.setCustomer(this.getCustomer().asShowDTO());
        return sellShowDTO;
    }
}
