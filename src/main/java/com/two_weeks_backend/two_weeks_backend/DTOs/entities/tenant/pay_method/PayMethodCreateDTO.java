package com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.pay_method;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.PayMethod;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class PayMethodCreateDTO extends BaseCreateDTO<PayMethod> {
    @NotBlank
    private String name;

    @NotNull
    private boolean isVirtual;

    @NotNull
    private boolean isJustForSell;

    @NotNull
    private Long tenantId;

    @Override
    public PayMethod asEntity() {
        PayMethod payMethod = new PayMethod();
        payMethod.setName(name);
        payMethod.setNameInTenant(name + this.getTenantId());
        payMethod.setIsVirtual(this.isVirtual());
        payMethod.setIsJustForSell(this.isJustForSell());
        payMethod.setTenant(this.getTenantEntity());
        return payMethod;
    }

    private Tenant getTenantEntity() {
        if (this.getTenantId() != null) {
            Tenant tenant = new Tenant();
            tenant.setId(this.getTenantId());
            return tenant;
        }
        return null;
    }
}
