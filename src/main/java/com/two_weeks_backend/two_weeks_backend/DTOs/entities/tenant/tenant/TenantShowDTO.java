package com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.tenant;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;

import lombok.*;

@Getter
@Setter
public class TenantShowDTO extends BaseShowDTO<Tenant> {
    private String name;
    private boolean isFlexModeActivated;
    private boolean activated;
}
