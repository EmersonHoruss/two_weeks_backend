package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.product.Product;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class ProductUpdateDTO extends BaseUpdateDTO<Product>{
    @NotNull
    private String type;
    @NotNull
    private String brand;
    @NotNull
    private String size;
    @NotNull
    @Min(0)
    private float purchasePrice;
    @NotNull
    @Min(0)
    private float sellPriceNormal;
    @NotNull
    @Min(0)
    private float sellPriceWholesale1;
    @NotNull
    @Min(0)
    private float sellPriceWholesale2;
    private String name;
    private String code;

    @Override
    public Product asEntity() {
        Product product = new Product();
        product.setId(this.getId());
        product.setType(this.getType());
        product.setBrand(this.getBrand());
        product.setSize(this.getSize());
        product.setPurchasePrice(this.getPurchasePrice());
        product.setSellPriceNormal(this.getSellPriceNormal());
        product.setSellPriceWholesale1(this.getSellPriceWholesale1());
        product.setSellPriceWholesale2(this.getSellPriceWholesale2());
        product.setName(this.getName());
        product.setCode(this.getCode());
        return product;
    }

}
