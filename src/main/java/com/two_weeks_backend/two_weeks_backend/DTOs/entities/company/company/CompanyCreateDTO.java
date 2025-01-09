package com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.company;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.company.Company;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class CompanyCreateDTO extends BaseCreateDTO<Company> {
    @NotBlank
    private String name;

    private String logo;

    private String RUC;

    private String standNumbers;

    private String place;

    @NotNull
    private Long tenantId;

    @Override
    public Company asEntity() {
        Company company = new Company();
        String name = this.getName().trim();
        String nameInTenant = name + this.getTenantId();
        String logo = this.getLogo().trim();
        String RUC = this.getRUC().trim();
        String standNumbers = this.getStandNumbers().trim();
        String place = this.getPlace().trim();

        company.setName(name);
        company.setNameInTenant(nameInTenant);
        company.setLogo(logo);
        company.setRUC(RUC);
        company.setStandNumbers(standNumbers);
        company.setPlace(place);
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
