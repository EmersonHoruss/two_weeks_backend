package com.two_weeks_backend.two_weeks_backend.DTOs.entities.detailSell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class DetailSellCreateDTO extends BaseCreateDTO<DetailSell> {
    @NotNull
    @Min(0)
    private float price;
    @NotNull
    @Min(0)
    private short amount;
    @Min(0)
    private float totalPrice;
    @NotNull
    private Long sell;
    @NotNull
    private Long product;

    @Override
    public DetailSell asEntity() {
        DetailSell detailSell = new DetailSell();
        detailSell.setPrice(this.getPrice());
        detailSell.setAmount(this.getAmount());
        detailSell.setTotalPrice(this.getTotalPrice());
        detailSell.setSell(this.getSellEntity());
        detailSell.setProduct(this.getProductEntity());
        return detailSell;
    }

    private Sell getSellEntity(){
        if(this.getSell()!=null){
            Sell sell = new Sell();
            sell.setId(this.getSell());
            return sell;
        }
        return null;
    }
    private Product getProductEntity(){
        if(this.getProduct()!=null){
            Product product = new Product();
            product.setId(this.getProduct());
            return product;
        }
        return null;
    }
}
