package com.two_weeks_backend.two_weeks_backend.entities.catalog;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.size.SizeShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "size", uniqueConstraints = {
        @UniqueConstraint(name = "uq_name_in_tenant", columnNames = "nameInTenant")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Size extends BaseEntity<SizeShowDTO> {
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "name_in_tenant", length = 255, unique = true, nullable = false)
    private String nameInTenant;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false, foreignKey = @ForeignKey(name = "fk_size_tenant"))
    private Tenant tenant;

    @PrePersist
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }

        this.nameInTenant = this.name + this.tenant.getId();
    }

    @Override
    public SizeShowDTO asShowDTO() {
        SizeShowDTO sizeShowDTO = new SizeShowDTO();
        sizeShowDTO.setId(this.getId());
        sizeShowDTO.setName(this.getName());
        sizeShowDTO.setActivated(this.getActivated());
        return sizeShowDTO;
    }
}
