package com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.type;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Type;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class TypeCreateDTO extends BaseCreateDTO<Type> {
    @NotBlank
    private String name;

    @NotNull
    private Long tenantId;

    @Override
    public Type asEntity() {
        Type type = new Type();
        String name = this.getName().trim();
        type.setName(name);
        type.setNameInTenant(name + this.getTenantId());
        type.setTenant(this.getTenantEntity());
        return type;
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
