package com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.company;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.company.Company;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class CompanyUpdateDTO extends BaseUpdateDTO<Company> {
    @NotBlank
    private String name;

    private String logo;

    private String RUC;

    private String standNumbers;

    private String place;

    @Override
    public Company asEntity() {
        Company company = new Company();
        String name = this.getName().trim();
        String logo = this.getLogo().trim();
        String RUC = this.getRUC().trim();
        String standNumbers = this.getStandNumbers().trim();
        String place = this.getPlace().trim();

        company.setId(this.getId());
        company.setName(name);
        company.setLogo(logo);
        company.setRUC(RUC);
        company.setStandNumbers(standNumbers);
        company.setPlace(place);
        return company;
    }
}
