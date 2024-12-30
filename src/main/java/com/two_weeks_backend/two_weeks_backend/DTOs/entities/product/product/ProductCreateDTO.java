package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.brand.Brand;
import com.two_weeks_backend.two_weeks_backend.entities.product.Product;
import com.two_weeks_backend.two_weeks_backend.entities.size.Size;
import com.two_weeks_backend.two_weeks_backend.entities.type.Type;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class ProductCreateDTO extends BaseCreateDTO<Product> {
    @NotNull
    @Min(0)
    private short stock;
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
    private String code;
    @NotNull
    private Long typeId;
    @NotNull
    private Long brandId;
    @NotNull
    private Long sizeId;

    @Override
    public Product asEntity() {
        Product product = new Product();
        product.setStock(this.getStock());
        product.setPurchasePrice(this.getPurchasePrice());
        product.setSellPriceNormal(this.getSellPriceNormal());
        product.setSellPriceWholesale1(this.getSellPriceWholesale1());
        product.setSellPriceWholesale2(this.getSellPriceWholesale2());
        String name = this.getTypeId() + "-" + this.getBrandId() + "-" + this.getSizeId();
        product.setName(name);
        product.setCode(this.getCode());
        product.setType(this.getTypeEntity());
        product.setBrand(this.getBrandEntity());
        product.setSize(this.getSizeEntity());
        return product;
    }

    private Type getTypeEntity() {
        if (this.getTypeId() != null) {
            Type type = new Type();
            type.setId(this.getTypeId());
            return type;
        }
        return null;
    }

    private Brand getBrandEntity() {
        if (this.getBrandId() != null) {
            Brand brand = new Brand();
            brand.setId(this.getBrandId());
            return brand;
        }
        return null;
    }

    private Size getSizeEntity() {
        if (this.getSizeId() != null) {
            Size size = new Size();
            size.setId(this.getSizeId());
            return size;
        }
        return null;
    }
}
