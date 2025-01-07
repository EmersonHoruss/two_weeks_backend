package com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.provider;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.purchase.Provider;

import lombok.*;

@Getter
@Setter
public class ProviderShowDTO extends BaseShowDTO<Provider> {
    private String name;
    private String alias;
    private String phone;
    private String whatsapp;
    private String bcpAccount;
    private String bcpAccountCCI;
    private String bbvaAccount;
    private String bbvaAccountCCI;
    private String yape;
    private String plin;
    private boolean activated;
}
