package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_change_detail;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellChange;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellChangeDetail;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellChangeDetailCreateDTO extends BaseCreateDTO<SellChangeDetail> {
    @NotNull
    @Min(1)
    private int amount;

    @NotNull
    private Long sellChangeId;

    @NotNull
    private Long changedProductId;

    @NotNull
    private Long newProductId;

    @Override
    public SellChangeDetail asEntity() {
        SellChangeDetail sellChangeDetail = new SellChangeDetail();
        sellChangeDetail.setAmount(this.getAmount());
        sellChangeDetail.setSellChange(this.getSellChangeEntity());
        sellChangeDetail.setChangedProduct(this.getChangedProductEntity());
        sellChangeDetail.setNewProduct(this.getNewProductEntity());
        return sellChangeDetail;
    }

    private SellChange getSellChangeEntity() {
        if (this.getSellChangeId() != null) {
            SellChange sellChange = new SellChange();
            sellChange.setId(this.getSellChangeId());
            return sellChange;
        }
        return null;
    }

    private Product getChangedProductEntity() {
        if (this.getChangedProductId() != null) {
            Product product = new Product();
            product.setId(this.getChangedProductId());
            return product;
        }
        return null;
    }

    private Product getNewProductEntity() {
        if (this.getNewProductId() != null) {
            Product product = new Product();
            product.setId(this.getNewProductId());
            return product;
        }
        return null;
    }
}
