package com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.provider;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.purchase.Provider;

import lombok.*;

@Getter
@Setter
public class ProviderActivatedDTO extends BaseActivatedDTO<Provider> {
    @Override
    public Provider asEntity() {
        Provider provider = new Provider();
        provider.setId(this.getId());
        provider.setActivated(this.getActivated());
        return provider;
    }
}
