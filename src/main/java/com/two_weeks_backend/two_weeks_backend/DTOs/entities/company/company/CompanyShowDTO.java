package com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.company;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.company.Company;

import lombok.*;

@Getter
@Setter
public class CompanyShowDTO extends BaseShowDTO<Company> {
    private String name;
    private String logo;
    private String companyCode;
    private String countryCode;
    private boolean activated;
}
