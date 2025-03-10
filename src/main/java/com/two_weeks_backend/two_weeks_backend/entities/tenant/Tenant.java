package com.two_weeks_backend.two_weeks_backend.entities.tenant;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.tenant.TenantShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.utils.BarCodeGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tenant", uniqueConstraints = {
        @UniqueConstraint(name = "uq_name", columnNames = "name")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tenant extends BaseEntity<TenantShowDTO> {
    @Column(name = "name", length = 255, unique = true, nullable = false)
    private String name;

    @Column(name = "is_flex_mode_activated", columnDefinition = "boolean default false", nullable = false)
    private Boolean isFlexModeActivated;

    @Column(name = "company_code", length = BarCodeGenerator.COMPANY_CODE_MAX_LENGTH, nullable = false)
    private String companyCode;

    @Column(name = "country_code", length = BarCodeGenerator.COUNTRY_CODE_LENGTH, nullable = false)
    private String countryCode;

    @Column(name = "last_consecutive_bar_code", length = BarCodeGenerator.EAN13_LENGTH, nullable = false)
    private String lastConsecutiveBarCode;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @PrePersist
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }
        if (this.isFlexModeActivated == null) {
            this.isFlexModeActivated = false;
        }
    }

    @Override
    public TenantShowDTO asShowDTO() {
        TenantShowDTO tenantShowDTO = new TenantShowDTO();
        tenantShowDTO.setId(this.getId());
        tenantShowDTO.setName(this.getName());
        tenantShowDTO.setFlexModeActivated(this.getIsFlexModeActivated());
        tenantShowDTO.setCompanyCode(this.getCompanyCode());
        tenantShowDTO.setCountryCode(this.getCountryCode());
        tenantShowDTO.setLastConsecutiveBarCode(this.getLastConsecutiveBarCode());
        tenantShowDTO.setActivated(this.getActivated());
        return tenantShowDTO;
    }
}
