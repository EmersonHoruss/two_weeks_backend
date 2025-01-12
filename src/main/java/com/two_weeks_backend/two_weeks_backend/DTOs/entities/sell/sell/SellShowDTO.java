package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell;

import lombok.*;

@Getter
@Setter
public class SellShowDTO {
    private Long id;
    private String date;
    private String clientName;
    private String clientDNI;
    private String clientRUC;
    private float total;
    private float totalVirtual;
    private float totalPhisical;
    private String sellerNickname;
    private boolean activated;
}
