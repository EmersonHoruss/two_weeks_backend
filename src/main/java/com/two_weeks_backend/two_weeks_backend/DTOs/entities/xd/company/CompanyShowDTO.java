package com.two_weeks_backend.two_weeks_backend.DTOs.entities.company;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.company.Company;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyShowDTO extends BaseShowDTO<Company> {
    private String name;
    private String logo;
    private String companyCode;
    private String countryCode;
    private boolean activated;
}
