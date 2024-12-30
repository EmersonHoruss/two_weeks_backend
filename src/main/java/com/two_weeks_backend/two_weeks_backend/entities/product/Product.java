package com.two_weeks_backend.two_weeks_backend.entities.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.product.ProductShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.brand.Brand;
import com.two_weeks_backend.two_weeks_backend.entities.size.Size;
import com.two_weeks_backend.two_weeks_backend.entities.type.Type;

import jakarta.persistence.*;
import lombok.*;

@SuppressWarnings("rawtypes")
@Entity
@Table(name = "product", uniqueConstraints = {
        @UniqueConstraint(name = "uq_product_name", columnNames = "name"),
        @UniqueConstraint(name = "uq_product_code", columnNames = "code")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product extends BaseEntity {
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
    @Column(length = 255, unique = true)
    private String name;
    @Column(length = 255, unique = true)
    private String code;
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_type"))
    private Type type;
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_brand"))
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "size_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_size"))
    private Size size;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @PrePersist
    private void prePersist() {
        if (activated == null) {
            activated = true;
        }
    }

    @Override
    public ProductShowDTO asShowDTO() {
        ProductShowDTO productShowDTO = new ProductShowDTO();
        productShowDTO.setId(this.getId());
        productShowDTO.setActivated(this.getActivated());
        productShowDTO.setStock(this.getStock());
        productShowDTO.setPurchasePrice(this.getPurchasePrice());
        productShowDTO.setSellPriceNormal(this.getSellPriceNormal());
        productShowDTO.setSellPriceWholesale1(this.getSellPriceWholesale1());
        productShowDTO.setSellPriceWholesale2(this.getSellPriceWholesale2());
        productShowDTO.setCode(this.getCode());
        productShowDTO.setType(this.getType().asShowDTO());
        productShowDTO.setBrand(this.getBrand().asShowDTO());
        productShowDTO.setSize(this.getSize().asShowDTO());
        return productShowDTO;
    }
}
