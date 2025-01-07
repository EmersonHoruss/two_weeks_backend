package com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.company;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.company.Company;

import lombok.*;

@Getter
@Setter
public class CompanyActivatedDTO extends BaseActivatedDTO<Company> {
    @Override
    public Company asEntity() {
        Company company = new Company();
        company.setId(this.getId());
        company.setActivated(this.getActivated());
        return company;
    }
}
