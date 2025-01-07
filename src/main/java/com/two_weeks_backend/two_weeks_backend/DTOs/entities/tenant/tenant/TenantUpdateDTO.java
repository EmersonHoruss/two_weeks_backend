package com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.tenant;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class TenantUpdateDTO extends BaseUpdateDTO<Tenant> {
    @NotBlank
    private String name;

    @NotNull
    private boolean isFlexModeActivated;

    @Override
    public Tenant asEntity() {
        Tenant tenant = new Tenant();
        tenant.setId(this.getId());
        tenant.setName(this.getName().trim());
        tenant.setIsFlexModeActivated(this.isFlexModeActivated());
        return tenant;
    }
}
