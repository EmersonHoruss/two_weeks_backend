package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_returning_detail;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellReturning;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellReturningDetail;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellReturningDetailCreateDTO extends BaseCreateDTO<SellReturningDetail> {
    @NotNull
    @Min(1)
    private int amount;

    @NotNull
    private Long sellReturningId;

    @NotNull
    private Long productId;

    @Override
    public SellReturningDetail asEntity() {
        SellReturningDetail sellReturningDetail = new SellReturningDetail();
        sellReturningDetail.setAmount(this.getAmount());
        sellReturningDetail.setSellReturning(this.getSellReturningEntity());
        sellReturningDetail.setProduct(this.getProductEntity());
        return sellReturningDetail;
    }

    private SellReturning getSellReturningEntity() {
        if (this.getSellReturningId() != null) {
            SellReturning sellReturning = new SellReturning();
            sellReturning.setId(this.getSellReturningId());
            return sellReturning;
        }
        return null;
    }

    private Product getProductEntity(){
        if(this.getProductId()!=null){
            Product product = new Product();
            product.setId(this.getProductId());
            return product;
        }
        return null;
    }
}
