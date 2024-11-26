package com.two_weeks_backend.two_weeks_backend.entities.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.product.ProductShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@SuppressWarnings("rawtypes")
@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product extends BaseEntity {
    @Column(length = 255, nullable = false)
    private String type;
    @Column(length = 255, nullable = false)
    private String brand;
    @Column(length = 255, nullable = false)
    private String size;
    @Column(nullable = false)
    private short stock;
    @Column(nullable = false)
    private float purchasePrice;
    @Column(nullable = false)
    private float sellPriceNormal;
    @Column(nullable = false)
    private float sellPriceWholesale1;
    @Column(nullable = false)
    private float sellPriceWholesale2;
    @Column(length = 255)
    private String name;
    @Column(length = 255)
    private String code;

    @Override
    public ProductShowDTO asShowDTO(){
        ProductShowDTO productShowDTO = new ProductShowDTO();
        productShowDTO.setId(this.getId());
        productShowDTO.setActivated(this.getActivated());
        productShowDTO.setType(this.getType());
        productShowDTO.setBrand(this.getBrand());
        productShowDTO.setSize(this.getSize());
        productShowDTO.setStock(this.getStock());
        productShowDTO.setPurchasePrice(this.getPurchasePrice());
        productShowDTO.setSellPriceNormal(this.getSellPriceNormal());
        productShowDTO.setSellPriceWholesale1(this.getSellPriceWholesale1());
        productShowDTO.setSellPriceWholesale2(this.getSellPriceWholesale2());
        productShowDTO.setName(this.getName());
        productShowDTO.setCode(this.getCode());
        return productShowDTO;
    }
}
