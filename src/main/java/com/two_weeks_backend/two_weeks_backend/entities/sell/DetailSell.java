package com.two_weeks_backend.two_weeks_backend.entities.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.detailSell.DetailSellShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.product.Product;

import jakarta.persistence.*;
import lombok.*;

@SuppressWarnings("rawtypes")
@Entity
@Table(name = "detail_sell")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetailSell extends BaseEntity {
    @Column(nullable = false)
    private float price;
    @Column(nullable = false)
    private short amount;
    @Column()
    private float totalPrice;
    @ManyToOne(optional = false)
    private Sell sell;
    @ManyToOne(optional = false)
    private Product product;

    @Override
    public DetailSellShowDTO asShowDTO(){
        DetailSellShowDTO detailSellShowDTO = new DetailSellShowDTO();
        detailSellShowDTO.setId(this.getId());
        detailSellShowDTO.setActivated(this.getActivated());
        detailSellShowDTO.setPrice(this.getPrice());
        detailSellShowDTO.setAmount(this.getAmount());
        detailSellShowDTO.setTotalPrice(this.getTotalPrice());
        detailSellShowDTO.setSell(this.getSell().asShowDTO());
        detailSellShowDTO.setProduct(this.getProduct().asShowDTO());
        return detailSellShowDTO;
    }
}
