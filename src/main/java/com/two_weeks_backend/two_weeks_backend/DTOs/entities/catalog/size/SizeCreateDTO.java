package com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.size;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Size;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SizeCreateDTO extends BaseCreateDTO<Size> {
    @NotBlank
    private String name;

    @NotNull
    private Long tenantId;

    @Override
    public Size asEntity() {
        Size size = new Size();
        String name = this.getName().trim();
        size.setName(name);
        size.setNameInTenant(name + this.getTenantId());
        size.setTenant(this.getTenantEntity());
        return size;
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
