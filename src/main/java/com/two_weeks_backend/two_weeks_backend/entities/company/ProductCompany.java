package com.two_weeks_backend.two_weeks_backend.entities.company;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.product_company.ProductCompanyShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductCompany extends BaseEntity<ProductCompanyShowDTO> {
    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "code", nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_company_product"))
    private Product product;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_company_company"))
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
    public ProductCompanyShowDTO asShowDTO() {
        ProductCompanyShowDTO productCompanyShowDTO = new ProductCompanyShowDTO();
        productCompanyShowDTO.setId(this.getId());
        productCompanyShowDTO.setActivated(this.getActivated());
        productCompanyShowDTO.setStock(this.getStock());
        productCompanyShowDTO.setCode(this.getCode());
        productCompanyShowDTO.setProduct(this.getProduct().asShowDTO());
        return productCompanyShowDTO;
    }

    public void addStock(int amount) {
        this.stock += amount;
    }

    public void subtractStock(int amount) {
        this.stock -= amount;
    }
}
