package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell;

import java.util.List;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail.SellDetailCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_pay_method.SellPayMethodCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.company.Company;
import com.two_weeks_backend.two_weeks_backend.entities.sell.DocumentType;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;
import com.two_weeks_backend.two_weeks_backend.entities.user.User;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellCreateDTO {
    @NotBlank
    private String date;

    private String clientName;

    private String clientDNI;

    private String clientRUC;

    @NotNull
    private DocumentType documentType;

    @NotNull
    private Long sellerId;

    @NotNull
    private Long tenantId;

    @NotNull
    private Long companyId;

    @NotNull
    @Size(min = 1)
    private List<SellDetailCreateDTO> details;

    @NotNull
    @Size(min = 1)
    private List<SellPayMethodCreateDTO> payMethods;

    public Sell asEntity() {
        Sell sell = new Sell();

        String clientName = this.getClientName() != null ? this.getClientName().trim() : null;
        String clientDNI = this.getClientDNI() != null ? this.getClientDNI().trim() : null;
        String clientRUC = this.getClientRUC() != null ? this.getClientRUC().trim() : null;

        sell.setDate(this.getDate());
        sell.setClientName(clientName);
        sell.setClientDNI(clientDNI);
        sell.setClientRUC(clientRUC);
        sell.setDocumentType(this.getDocumentType());
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
