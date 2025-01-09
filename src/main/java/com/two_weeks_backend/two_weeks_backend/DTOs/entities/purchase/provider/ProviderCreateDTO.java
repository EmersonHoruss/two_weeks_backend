package com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.provider;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.purchase.Provider;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class ProviderCreateDTO extends BaseCreateDTO<Provider> {
    @NotBlank
    private String name;

    @NotBlank
    private String alias;

    @Size(max = Provider.PROVIDER_PHONE_LENGTH)
    private String phone;

    @Size(max = Provider.PROVIDER_PHONE_LENGTH)
    private String whatsapp;

    private Long tenantId;

    @Override
    public Provider asEntity() {
        Provider provider = new Provider();
        String phone = this.getPhone() != null ? this.getPhone().trim() : null;
        String whatsapp = this.getWhatsapp() != null ? this.getWhatsapp().trim() : null;

        provider.setName(this.getName());
        provider.setAlias(this.getAlias());
        provider.setAliasInTenant(this.getAlias() + this.getTenantId());
        provider.setPhone(phone);
        provider.setWhatsapp(whatsapp);
        provider.setTenant(this.getTenantEntity());
        return provider;
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
