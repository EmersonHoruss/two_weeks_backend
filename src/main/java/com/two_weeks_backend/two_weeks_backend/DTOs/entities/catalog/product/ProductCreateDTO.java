package com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Brand;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Size;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Type;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class ProductCreateDTO extends BaseCreateDTO<Product> {
    @Min(0)
    @NotNull
    private float sellPriceNormal;

    @Min(0)
    @NotNull
    private float sellPriceAuction;

    @Min(0)
    @NotNull
    private float sellPriceWholesale;

    @Min(0)
    @NotNull
    private float sellPriceWholesaleSuper;

    @NotNull
    private Long typeId;

    @NotNull
    private Long brandId;

    @NonNull
    private Long sizeId;

    @NotNull
    private Long tenantId;

    @Override
    public Product asEntity() {
        Product product = new Product();
        product.setSellPriceNormal(this.getSellPriceNormal());
        product.setSellPriceAuction(this.getSellPriceAuction());
        product.setSellPriceWholesale(this.getSellPriceWholesale());
        product.setSellPriceWholesaleSuper(this.getSellPriceWholesaleSuper());
        String nameInTenant = this.getTypeId() + "-" + this.getBrandId() + "-" + this.getSizeId() + "-"
                + this.getTenantId();
        product.setNameInTenant(nameInTenant);
        product.setType(this.getTypeEntity());
        product.setBrand(this.getBrandEntity());
        product.setSize(this.getSizeEntity());
        product.setTenant(this.getTenantEntity());
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

    private Tenant getTenantEntity() {
        if (this.getTenantId() != null) {
            Tenant tenant = new Tenant();
            tenant.setId(this.getTenantId());
            return tenant;
        }
        return null;
    }
}
