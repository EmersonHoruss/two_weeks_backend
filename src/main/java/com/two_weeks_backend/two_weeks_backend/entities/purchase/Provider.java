package com.two_weeks_backend.two_weeks_backend.entities.purchase;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.provider.ProviderShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "provider")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Provider extends BaseEntity<ProviderShowDTO> {
    public final static int PROVIDER_PHONE_LENGTH = 9;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "phone", length = Provider.PROVIDER_PHONE_LENGTH)
    private String phone;

    @Column(name = "whatsapp", length = Provider.PROVIDER_PHONE_LENGTH)
    private String whatsapp;

    @Column(name = "bcp_account")
    private String bcpAccount;

    @Column(name = "bcp_account_cci")
    private String bcpAccountCCI;

    @Column(name = "bbva_account")
    private String bbvaAccount;

    @Column(name = "bbva_account_cci")
    private String bbvaAccountCCI;

    @Column(name = "yape")
    private String yape;

    @Column(name = "plin")
    private String plin;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @PrePersist
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }
    }

    @Override
    public ProviderShowDTO asShowDTO() {
        ProviderShowDTO providerShowDTO = new ProviderShowDTO();
        providerShowDTO.setId(this.getId());
        providerShowDTO.setActivated(this.getActivated());
        providerShowDTO.setName(this.getName());
        providerShowDTO.setPhone(this.getPhone());
        providerShowDTO.setWhatsapp(this.getWhatsapp());
        providerShowDTO.setBcpAccount(this.getBcpAccount());
        providerShowDTO.setBcpAccountCCI(this.getBbvaAccountCCI());
        providerShowDTO.setBbvaAccount(this.getBbvaAccount());
        providerShowDTO.setBbvaAccountCCI(this.getBbvaAccountCCI());
        providerShowDTO.setYape(this.getYape());
        providerShowDTO.setPlin(this.getPlin());
        return providerShowDTO;
    }

}
