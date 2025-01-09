package com.two_weeks_backend.two_weeks_backend.entities.purchase;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.provider.ProviderShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "provider", uniqueConstraints = {
        @UniqueConstraint(name = "uq_alias_in_tenant", columnNames = "aliasInTenant")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Provider extends BaseEntity<ProviderShowDTO> {
    public final static int PROVIDER_PHONE_LENGTH = 9;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "alias", length = 255, nullable = false)
    private String alias;

    @Column(name = "alias", length = 255, nullable = false, unique = true)
    private String aliasInTenant;

    @Column(name = "phone", length = Provider.PROVIDER_PHONE_LENGTH)
    private String phone;

    @Column(name = "whatsapp", length = Provider.PROVIDER_PHONE_LENGTH)
    private String whatsapp;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false, foreignKey = @ForeignKey(name = "fk_provider_tenant"))
    private Tenant tenant;

    @PrePersist
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }

        this.aliasInTenant = this.alias + this.getTenant().getId();
    }

    @Override
    public ProviderShowDTO asShowDTO() {
        ProviderShowDTO providerShowDTO = new ProviderShowDTO();
        providerShowDTO.setId(this.getId());
        providerShowDTO.setActivated(this.getActivated());
        providerShowDTO.setName(this.getName());
        providerShowDTO.setAlias(this.getAlias());
        providerShowDTO.setPhone(this.getPhone());
        providerShowDTO.setWhatsapp(this.getWhatsapp());
        return providerShowDTO;
    }

}
