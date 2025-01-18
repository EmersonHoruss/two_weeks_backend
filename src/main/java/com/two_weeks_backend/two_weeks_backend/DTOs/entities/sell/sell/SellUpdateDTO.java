package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.DocumentType;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class SellUpdateDTO extends BaseUpdateDTO<Sell> {
    @NotBlank
    private String date;

    private String clientName;

    private String clientDNI;

    private String clientRUC;

    @NotNull
    private DocumentType documentType;

    @NotNull
    private float total;

    @NotNull
    private float totalVirtual;

    @NotNull
    private float totalPhisical;

    @NotNull
    private boolean isReturned;

    @NotNull
    private boolean isChanged;

    public Sell asEntity() {
        Sell sell = new Sell();

        String clientName = this.getClientName() != null ? this.getClientName().trim() : null;
        String clientDNI = this.getClientDNI() != null ? this.getClientDNI().trim() : null;
        String clientRUC = this.getClientRUC() != null ? this.getClientRUC().trim() : null;

        if ((this.totalPhisical + this.totalVirtual) != this.total)
            throw new RuntimeException("El total físico más el total virtual no coincide con el total");

        sell.setDate(this.getDate());
        sell.setClientName(clientName);
        sell.setClientDNI(clientDNI);
        sell.setClientRUC(clientRUC);
        sell.setDocumentType(this.getDocumentType());
        sell.setTotal(this.getTotal());
        sell.setTotalVirtual(this.getTotalVirtual());
        sell.setTotalPhisical(this.getTotalPhisical());
        sell.setIsReturned(this.isReturned());
        sell.setIsChanged(this.isChanged());
        return sell;
    }
}
