package com.two_weeks_backend.two_weeks_backend.entities.company;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.CompanyShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.product.BarCode;

import jakarta.persistence.*;
import lombok.*;

@SuppressWarnings("rawtypes")
@Entity
@Table(name = "product", uniqueConstraints = {
        @UniqueConstraint(name = "uq_company_code", columnNames = "companyCode")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Company extends BaseEntity {
    @Column(nullable = false)
    private String name;

    private String logo;

    @Column(nullable = false, unique = true)
    private String companyCode;

    @Column(nullable = false)
    private String countryCode;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @PrePersist
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }

        if (this.companyCode != null && this.companyCode.length() < 4) {
            String format = "%" + BarCode.COMPANY_CODE_MAX_LENGTH + "s";
            this.companyCode = String.format(format, this.companyCode).replace(" ", "0");
        }

        if (this.countryCode != null && this.countryCode.length() < 4) {
            String format = "%" + BarCode.COUNTRY_CODE_MAX_LENGTH + "s";
            this.countryCode = String.format(format, this.countryCode).replace(" ", "0");
        }

    }

    @Override
    public CompanyShowDTO asShowDTO() {
        CompanyShowDTO companyShowDTO = new CompanyShowDTO();
        companyShowDTO.setId(this.getId());
        companyShowDTO.setActivated(this.getActivated());
        companyShowDTO.setName(this.getName());
        companyShowDTO.setLogo(this.getLogo());
        companyShowDTO.setCompanyCode(this.getCompanyCode());
        companyShowDTO.setCountryCode(this.getCountryCode());
        return companyShowDTO;
    }
}
