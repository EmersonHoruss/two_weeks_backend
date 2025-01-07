package com.two_weeks_backend.two_weeks_backend.entities.purchase;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.product_provider.ProductProviderShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductProvider extends BaseEntity<ProductProviderShowDTO> {
    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_provider_product"))
    private Product product;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_provider_provider"))
    private Provider provider;

    @PrePersist
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }
    }

    @Override
    public ProductProviderShowDTO asShowDTO() {
        ProductProviderShowDTO productProviderShowDTO = new ProductProviderShowDTO();
        productProviderShowDTO.setId(this.getId());
        productProviderShowDTO.setActivated(this.getActivated());
        productProviderShowDTO.setProduct(this.getProduct().asShowDTO());
        return productProviderShowDTO;
    }
}
