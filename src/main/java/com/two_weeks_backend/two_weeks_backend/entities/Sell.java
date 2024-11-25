package com.two_weeks_backend.two_weeks_backend.entities;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.SellShowDTO;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

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
