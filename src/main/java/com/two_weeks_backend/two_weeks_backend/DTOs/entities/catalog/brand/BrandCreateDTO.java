package com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.brand;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Brand;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class BrandCreateDTO extends BaseCreateDTO<Brand> {
    @NotBlank
    private String name;

    @NotNull
    private Long tenantId;

    @Override
    public Brand asEntity() {
        Brand brand = new Brand();
        String name = this.getName().trim();
        brand.setName(name);
        brand.setNameInTenant(name + this.getTenantId());
        brand.setTenant(this.getTenantEntity());
        return brand;
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
