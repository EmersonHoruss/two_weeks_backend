package com.two_weeks_backend.two_weeks_backend.entities.company;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.company.CompanyShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;
import com.two_weeks_backend.two_weeks_backend.utils.Constants;

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

    @Column(name = "name_in_tenant", length = 255, unique = true, nullable = false)
    private String nameInTenant;

    @Column(name = "logo", length = 255)
    private String logo;

    @Column(name = "RUC", length = Constants.RUC_LENGTH)
    private String RUC;

    @Column(name = "stands_number", length = 255)
    private String standNumbers;

    @Column(name = "place", length = 255)
    private String place;

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
        companyShowDTO.setRUC(this.getRUC());
        companyShowDTO.setStandNumbers(this.getStandNumbers());
        companyShowDTO.setPlace(this.getPlace());
        companyShowDTO.setActivated(this.getActivated());
        return companyShowDTO;
    }
}
