package com.two_weeks_backend.two_weeks_backend.entities.tenant;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.pay_method.PayMethodShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pay_method", uniqueConstraints = {
        @UniqueConstraint(name = "uq_name_in_tenant", columnNames = "nameInTenant")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PayMethod extends BaseEntity<PayMethodShowDTO> {
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "name_in_tenant", length = 255, unique = true, nullable = false)
    private String nameInTenant;

    @Column(name = "is_virtual", nullable = false)
    private Boolean isVirtual;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pay_method_tenant"))
    private Tenant tenant;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @PrePersist
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }

        this.nameInTenant = this.name + this.tenant.getId();
    }

    @Override
    public PayMethodShowDTO asShowDTO() {
        PayMethodShowDTO payMethodShowDTO = new PayMethodShowDTO();
        payMethodShowDTO.setId(this.getId());
        payMethodShowDTO.setActivated(this.getActivated());
        payMethodShowDTO.setName(this.getName());
        payMethodShowDTO.setVirtual(this.getIsVirtual());
        return payMethodShowDTO;
    }
}
