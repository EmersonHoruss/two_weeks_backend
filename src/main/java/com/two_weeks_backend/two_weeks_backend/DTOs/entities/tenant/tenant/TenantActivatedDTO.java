package com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.tenant;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;

import lombok.*;

@Getter
@Setter
public class TenantActivatedDTO extends BaseActivatedDTO<Tenant> {

    @Override
    public Tenant asEntity() {
        Tenant tenant = new Tenant();
        tenant.setId(this.getId());
        tenant.setActivated(this.getActivated());
        return tenant;
    }

}
