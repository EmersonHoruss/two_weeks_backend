package com.two_weeks_backend.two_weeks_backend.entities.catalog;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.product.ProductShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product", uniqueConstraints = {
        @UniqueConstraint(name = "uq_name_in_tenant", columnNames = "nameInTenant")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product extends BaseEntity<ProductShowDTO> {
    @Column(name = "sell_price_normal", nullable = false)
    private float sellPriceNormal;

    @Column(name = "sell_price_auction", nullable = false)
    private float sellPriceAuction;

    @Column(name = "sell_price_wholesale", nullable = false)
    private float sellPriceWholesale;

    @Column(name = "sell_price_wholesale_super", nullable = false)
    private float sellPriceWholesaleSuper;

    @Column(name = "name_in_tenant", nullable = false, unique = true)
    private String nameInTenant;

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
    @JoinColumn(name = "tenant_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_tenant"))
    private Tenant tenant;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @PrePersist
    private void prePersist() {
        if (activated == null) {
            activated = true;
        }

        this.nameInTenant = this.type.getId() + "-" + this.brand.getId() + "-" + this.size.getId() + "-"
                + this.tenant.getId();
    }

    @Override
    public ProductShowDTO asShowDTO() {
        ProductShowDTO productShowDTO = new ProductShowDTO();
        productShowDTO.setId(this.getId());
        productShowDTO.setSellPriceNormal(this.getSellPriceNormal());
        productShowDTO.setSellPriceAuction(this.getSellPriceAuction());
        productShowDTO.setSellPriceWholesale(this.getSellPriceWholesale());
        productShowDTO.setSellPriceWholesaleSuper(this.getSellPriceWholesaleSuper());
        productShowDTO.setType(this.getType().asShowDTO());
        productShowDTO.setBrand(this.getBrand().asShowDTO());
        productShowDTO.setSize(this.getSize().asShowDTO());
        return productShowDTO;
    }
}
