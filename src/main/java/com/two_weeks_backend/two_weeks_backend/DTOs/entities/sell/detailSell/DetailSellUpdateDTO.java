package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.detailSell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.product.ProductSet;
import com.two_weeks_backend.two_weeks_backend.entities.sell.DetailSell;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class DetailSellUpdateDTO extends BaseUpdateDTO<DetailSell>{
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
        detailSell.setId(this.getId());
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
    private ProductSet getProductEntity(){
        if(this.getProduct()!=null){
            ProductSet productSet = new ProductSet();
            productSet.setId(this.getProduct());
            return productSet;
        }
        return null;
    }
}
