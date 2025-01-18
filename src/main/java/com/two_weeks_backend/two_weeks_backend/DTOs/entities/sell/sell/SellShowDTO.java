package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.DocumentType;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;

import lombok.*;

@Getter
@Setter
public class SellShowDTO extends BaseShowDTO<Sell> {
    private String date;
    private String clientName;
    private String clientDNI;
    private String clientRUC;
    private DocumentType documentType;
    private float total;
    private float totalVirtual;
    private float totalPhisical;
    private String sellerNickname;
    private boolean isReturned;
    private boolean isChanged;
    private boolean activated;
}
