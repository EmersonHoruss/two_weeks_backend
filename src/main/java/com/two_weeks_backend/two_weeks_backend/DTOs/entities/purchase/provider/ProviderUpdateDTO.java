package com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.provider;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.purchase.Provider;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class ProviderUpdateDTO extends BaseUpdateDTO<Provider> {
    @NotBlank
    private String name;

    @NotBlank
    private String alias;

    @Size(max = Provider.PROVIDER_PHONE_LENGTH)
    private String phone;

    @Size(max = Provider.PROVIDER_PHONE_LENGTH)
    private String whatsapp;

    @Override
    public Provider asEntity() {
        Provider provider = new Provider();
        String phone = this.getPhone() != null ? this.getPhone().trim() : null;
        String whatsapp = this.getWhatsapp() != null ? this.getWhatsapp().trim() : null;

        provider.setId(this.getId());
        provider.setName(this.getName());
        provider.setAlias(this.getAlias());
        provider.setPhone(phone);
        provider.setWhatsapp(whatsapp);
        return provider;
    }
}
