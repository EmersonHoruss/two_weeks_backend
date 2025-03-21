package com.two_weeks_backend.two_weeks_backend.entities.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail.SellDetailShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.PriceType;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sell_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SellDetail extends BaseEntity<SellDetailShowDTO> {
    @Column(name = "amount", nullable = false)
    private int amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "price_type", nullable = false)
    private PriceType priceType;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "sub_total", nullable = false)
    private float subTotal;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @ManyToOne
    @JoinColumn(name = "sell", nullable = false, foreignKey = @ForeignKey(name = "fk_sell_detail_sell"))
    private Sell sell;

    @ManyToOne
    @JoinColumn(name = "product", nullable = false, foreignKey = @ForeignKey(name = "fk_sell_detail_product"))
    private Product product;

    @PrePersist
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }
        this.subTotal = this.getAmount() * this.price;
    }

    public SellDetailShowDTO asShowDTO() {
        SellDetailShowDTO sellDetailShowDTO = new SellDetailShowDTO();
        sellDetailShowDTO.setId(this.getId());
        sellDetailShowDTO.setActivated(this.getActivated());
        sellDetailShowDTO.setAmount(this.getAmount());
        sellDetailShowDTO.setPriceType(this.priceType);
        sellDetailShowDTO.setPrice(this.getPrice());
        sellDetailShowDTO.setSubTotal(this.getSubTotal());
        sellDetailShowDTO.setProduct(this.getProduct().asShowDTO());
        return sellDetailShowDTO;
    }
}
