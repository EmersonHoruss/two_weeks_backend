package com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.company;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.company.Company;
import com.two_weeks_backend.two_weeks_backend.utils.BarCodeGenerator;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class CompanyUpdateDTO extends BaseUpdateDTO<Company> {
    @NotBlank
    private String name;

    private String logo;

    @NotBlank
    @Size(max = BarCodeGenerator.COMPANY_CODE_MAX_LENGTH)
    private String companyCode;

    @NotBlank
    @Size(min = BarCodeGenerator.COUNTRY_CODE_LENGTH, max = BarCodeGenerator.COUNTRY_CODE_LENGTH)
    private String countryCode;

    @Override
    public Company asEntity() {
        Company company = new Company();
        String name = this.getName().trim();
        String logo = this.getLogo().trim();
        String companyCode = this.getCompanyCode().trim();
        String countryCode = this.getCountryCode().trim();
        company.setId(this.getId());
        company.setName(name);
        company.setLogo(logo);
        company.setCompanyCode(companyCode);
        company.setCountryCode(countryCode);
        return company;
    }
}
