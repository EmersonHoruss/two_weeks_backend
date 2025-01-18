package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.company.Company;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;
import com.two_weeks_backend.two_weeks_backend.entities.user.User;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellCreateDTO extends BaseCreateDTO<Sell> {
    @NotNull
    private Long sellerId;

    @NotNull
    private Long tenantId;

    @NotNull
    private Long companyId;

    public Sell asEntity() {
        Sell sell = new Sell();
        sell.setDate("");
        sell.setTotal(0);
        sell.setTotalVirtual(0);
        sell.setTotalPhisical(0);
        sell.setIsReturned(false);
        sell.setIsChanged(false);
        sell.setSeller(this.getSellerEntity());
        sell.setTenant(this.getTenantEntity());
        sell.setCompany(this.getCompanyEntity());
        return sell;
    }

    private User getSellerEntity() {
        if (this.getSellerId() != null) {
            User user = new User();
            user.setId(this.getSellerId());
            return user;
        }
        return null;
    }

    private Tenant getTenantEntity() {
        if (this.getTenantId() != null) {
            Tenant tenant = new Tenant();
            tenant.setId(this.getTenantId());
            return tenant;
        }
        return null;
    }

    private Company getCompanyEntity() {
        if (this.getCompanyId() != null) {
            Company company = new Company();
            company.setId(this.getCompanyId());
            return company;
        }
        return null;
    }
}
