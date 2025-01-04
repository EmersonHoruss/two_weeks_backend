package com.two_weeks_backend.two_weeks_backend.DTOs.entities.company;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.company.Company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyUpdateDTO extends BaseUpdateDTO<Company> {
    @NotBlank
    private String name;

    private String logo;

    @NotBlank
    @Size(max = 4)
    @Pattern(regexp = "^[0-9]*$")
    private String companyCode;

    @NotBlank
    @Size(max = 3)
    @Pattern(regexp = "^[0-9]*$")
    private String countryCode;

    @Override
    public Company asEntity() {
        Company company = new Company();
        company.setId(this.getId());
        company.setName(this.getName());
        company.setLogo(this.getLogo());
        company.setCompanyCode(this.getCompanyCode());
        company.setCountryCode(this.getCountryCode());
        return company;
    }
}
