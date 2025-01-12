package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell;

import java.util.List;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail.SellDetailCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_pay_method.SellPayMethodCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.DocumentType;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellCreateDTO {
    @NotNull
    private String date;

    private String clientName;

    private String clientDNI;

    private String clientRUC;

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
        
        return sell;
    }
}
