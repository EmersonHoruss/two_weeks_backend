package com.two_weeks_backend.two_weeks_backend.entities.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_returning_detail.SellReturningDetailShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sell_returning_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SellReturningDetail extends BaseEntity<SellReturningDetailShowDTO> {
    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @ManyToOne
    @JoinColumn(name = "sell_returning", nullable = false, foreignKey = @ForeignKey(name = "fk_sell_returning_detail_sell_returning"))
    private SellReturning sellReturning;

    @ManyToOne
    @JoinColumn(name = "product", nullable = false, foreignKey = @ForeignKey(name = "fk_sell_returning_detail_product"))
    private Product product;

    @Override
    public SellReturningDetailShowDTO asShowDTO() {
        SellReturningDetailShowDTO sellReturningDetailShowDTO = new SellReturningDetailShowDTO();
        sellReturningDetailShowDTO.setId(this.getId());
        sellReturningDetailShowDTO.setAmount(this.getAmount());
        sellReturningDetailShowDTO.setProduct(this.getProduct().asShowDTO());
        return sellReturningDetailShowDTO;
    }
}
