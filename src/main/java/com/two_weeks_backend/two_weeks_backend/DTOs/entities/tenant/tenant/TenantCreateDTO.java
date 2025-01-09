package com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.tenant;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;
import com.two_weeks_backend.two_weeks_backend.utils.BarCodeGenerator;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class TenantCreateDTO extends BaseCreateDTO<Tenant> {
    @NotBlank
    private String name;

    @NotNull
    private boolean isFlexModeActivated;

    @NotBlank
    @Size(max = BarCodeGenerator.COMPANY_CODE_MAX_LENGTH)
    private String companyCode;

    @NotBlank
    @Size(min = BarCodeGenerator.COUNTRY_CODE_LENGTH, max = BarCodeGenerator.COUNTRY_CODE_LENGTH)
    private String countryCode;

    @Override
    public Tenant asEntity() {
        Tenant tenant = new Tenant();
        String name = this.getName().trim();
        String companyCode = this.getCompanyCode().trim();
        String countryCode = this.getCountryCode().trim();

        tenant.setName(name);
        tenant.setIsFlexModeActivated(this.isFlexModeActivated());
        tenant.setCompanyCode(companyCode);
        tenant.setCountryCode(countryCode);
        return tenant;
    }
}
