package com.two_weeks_backend.two_weeks_backend.entities.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.product.ProductShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.brand.Brand;
import com.two_weeks_backend.two_weeks_backend.entities.company.Company;
import com.two_weeks_backend.two_weeks_backend.entities.size.Size;
import com.two_weeks_backend.two_weeks_backend.entities.type.Type;

import jakarta.persistence.*;
import lombok.*;

@SuppressWarnings("rawtypes")
@Entity
@Table(name = "product", uniqueConstraints = {
        @UniqueConstraint(name = "uq_product_code", columnNames = "code")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product extends BaseEntity {
    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private float sellPriceNormal;

    @Column(nullable = false)
    private float sellPriceAuction;

    @Column(nullable = false)
    private float sellPriceWholesale;

    @Column(nullable = false)
    private float sellPriceWholesaleSuper;

    @Column(nullable = false, length = 13, unique = true)
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

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_company"))
    private Company company;

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
        productShowDTO.setSellPriceNormal(this.getSellPriceNormal());
        productShowDTO.setSellPriceAuction(this.getSellPriceAuction());
        productShowDTO.setSellPriceWholesale(this.getSellPriceWholesale());
        productShowDTO.setSellPriceWholesaleSuper(this.getSellPriceWholesaleSuper());
        productShowDTO.setCode(this.getCode());
        productShowDTO.setType(this.getType().asShowDTO());
        productShowDTO.setBrand(this.getBrand().asShowDTO());
        productShowDTO.setSize(this.getSize().asShowDTO());
        productShowDTO.setCompany(this.getCompany().asShowDTO());
        return productShowDTO;
    }
}
