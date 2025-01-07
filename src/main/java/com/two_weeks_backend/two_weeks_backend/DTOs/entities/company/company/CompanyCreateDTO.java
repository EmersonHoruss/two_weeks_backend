package com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.company;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.company.Company;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;
import com.two_weeks_backend.two_weeks_backend.utils.BarCodeGenerator;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class CompanyCreateDTO extends BaseCreateDTO<Company> {
    @NotBlank
    private String name;

    private String logo;

    @NotBlank
    @Size(max = BarCodeGenerator.COMPANY_CODE_MAX_LENGTH)
    private String companyCode;

    @NotBlank
    @Size(min = BarCodeGenerator.COUNTRY_CODE_LENGTH, max = BarCodeGenerator.COUNTRY_CODE_LENGTH)
    private String countryCode;

    @NotNull
    private Long tenantId;

    @Override
    public Company asEntity() {
        Company company = new Company();
        String name = this.getName().trim();
        String nameInTenant = name + this.getTenantId();
        String logo = this.getLogo().trim();
        String companyCode = this.getCompanyCode().trim();
        String countryCode = this.getCountryCode().trim();
        company.setName(name);
        company.setNameInTenant(nameInTenant);
        company.setLogo(logo);
        company.setCompanyCode(companyCode);
        company.setCountryCode(countryCode);
        company.setTenant(this.getTenantEntity());
        return company;
    }

    public Tenant getTenantEntity() {
        if (this.getTenantId() != null) {
            Tenant tenant = new Tenant();
            tenant.setId(this.getTenantId());
            return tenant;
        }
        return null;
    }
}
