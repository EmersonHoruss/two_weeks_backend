package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell;

import java.util.List;

import org.w3c.dom.DocumentType;

import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellUpdateDTO {
    @NotNull
    private Long id;

    @NonNull
    private String date;

    private String clientName;

    private String clientDNI;

    private String clientRUC;

    @NotNull
    private DocumentType documentType;

    private List<> updatedDetails;

    private List<> addedDetails;

    private List<> changedDetails;

    private List<> updatedPayMethods;

    private List<> addedPayMethods;

    public Sell asEntity() {
        Sell sell = new Sell();
        return sell;
    }
}
