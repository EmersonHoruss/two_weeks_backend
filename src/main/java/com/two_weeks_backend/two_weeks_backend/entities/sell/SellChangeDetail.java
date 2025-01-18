package com.two_weeks_backend.two_weeks_backend.entities.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_change_detail.SellChangeDetailShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sell_change_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SellChangeDetail extends BaseEntity<SellChangeDetailShowDTO> {
    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @ManyToOne
    @JoinColumn(name = "sell_change_id", nullable = false, foreignKey = @ForeignKey(name = "fk_sell_change_detail_sell_change"))
    private SellChange sellChange;

    @ManyToOne
    @JoinColumn(name = "changed_product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_sell_change_detail_changed_product"))
    private Product changedProduct;

    @ManyToOne
    @JoinColumn(name = "new_product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_sell_change_detail_new_product"))
    private Product newProduct;

    @Override
    public SellChangeDetailShowDTO asShowDTO() {
        SellChangeDetailShowDTO sellChangeDetailShowDTO = new SellChangeDetailShowDTO();
        sellChangeDetailShowDTO.setId(this.getId());
        sellChangeDetailShowDTO.setAmount(this.getAmount());
        sellChangeDetailShowDTO.setChangedProduct(this.getChangedProduct().asShowDTO());
        sellChangeDetailShowDTO.setNewProduct(this.getNewProduct().asShowDTO());
        return sellChangeDetailShowDTO;
    }
}
