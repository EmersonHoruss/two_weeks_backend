package com.two_weeks_backend.two_weeks_backend.entities.company;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.company.CompanyShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;
import com.two_weeks_backend.two_weeks_backend.utils.BarCodeGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company", uniqueConstraints = {
        @UniqueConstraint(name = "uq_name_in_tenant", columnNames = "nameInTenant")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Company extends BaseEntity<CompanyShowDTO> {
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "logo", length = 255)
    private String logo;

    @Column(name = "company_code", length = BarCodeGenerator.COMPANY_CODE_MAX_LENGTH, nullable = false)
    private String companyCode;

    @Column(name = "country_code", length = BarCodeGenerator.COUNTRY_CODE_LENGTH, nullable = false)
    private String countryCode;

    @Column(name = "name_in_tenant", length = 255, unique = true, nullable = false)
    private String nameInTenant;

    @Column(name = "last_consecutive_bar_code", length = BarCodeGenerator.EAN13_LENGTH, nullable = false)
    private String lastConsecutiveBarCode;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false, foreignKey = @ForeignKey(name = "fk_company_tenant"))
    private Tenant tenant;

    @PrePersist
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }

        this.nameInTenant = this.name + this.tenant.getId();
    }

    @Override
    public CompanyShowDTO asShowDTO() {
        CompanyShowDTO companyShowDTO = new CompanyShowDTO();
        companyShowDTO.setId(this.getId());
        companyShowDTO.setName(this.getName());
        companyShowDTO.setLogo(this.getLogo());
        companyShowDTO.setCompanyCode(this.getCompanyCode());
        companyShowDTO.setCountryCode(this.getCountryCode());
        companyShowDTO.setActivated(this.getActivated());
        return companyShowDTO;
    }
}
