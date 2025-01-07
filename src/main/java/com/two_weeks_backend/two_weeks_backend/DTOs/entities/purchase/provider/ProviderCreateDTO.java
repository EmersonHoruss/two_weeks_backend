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

    private String bcpAccount;

    private String bcpAccountCCI;

    private String bbvaAccount;

    private String bbvaAccountCCI;

    private String yape;

    private String plin;

    private Long tenantId;

    @Override
    public Provider asEntity() {
        Provider provider = new Provider();
        provider.setName(this.getName());
        provider.setAlias(this.getAlias());
        provider.setAliasInTenant(this.getAlias() + this.getTenantId());
        provider.setPhone(this.getPhone());
        provider.setWhatsapp(this.getWhatsapp());
        provider.setBcpAccount(this.getBcpAccount());
        provider.setBcpAccountCCI(this.getBcpAccountCCI());
        provider.setBbvaAccount(this.getBbvaAccount());
        provider.setBbvaAccountCCI(this.getBbvaAccountCCI());
        provider.setYape(this.getYape());
        provider.setPlin(this.getPlin());
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
