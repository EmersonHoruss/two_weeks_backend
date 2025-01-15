package com.two_weeks_backend.two_weeks_backend.entities.sell;

import java.util.List;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell.SellShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.company.Company;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.PayMethod;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;
import com.two_weeks_backend.two_weeks_backend.entities.user.User;
import com.two_weeks_backend.two_weeks_backend.utils.Constants;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sell")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Sell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @Column(name = "date", length = 255, nullable = false)
    private String date;

    @Column(name = "client_name", length = 255)
    private String clientName;

    @Column(name = "client_DNI", length = Constants.DNI_LENGTH)
    private String clientDNI;

    @Column(name = "client_RUC", length = Constants.RUC_LENGTH)
    private String clientRUC;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false, length = 255)
    private DocumentType documentType;

    @Column(name = "total", nullable = false)
    private float total;

    @Column(name = "total_virtual", nullable = false)
    private float totalVirtual;

    @Column(name = "total_phisical", nullable = false)
    private float totalPhisical;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false, foreignKey = @ForeignKey(name = "fk_sell_seller"))
    private User seller;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false, foreignKey = @ForeignKey(name = "fk_sell_tenant"))
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false, foreignKey = @ForeignKey(name = "fk_sell_company"))
    private Company company;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @PrePersist
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }
    }

    public SellShowDTO asShowDTO() {
        SellShowDTO sellShowDTO = new SellShowDTO();
        String clientName = this.getClientName() != null ? this.getClientName().trim() : null;
        String clientDNI = this.getClientDNI() != null ? this.getClientDNI().trim() : null;
        String clientRUC = this.getClientRUC() != null ? this.getClientRUC().trim() : null;

        sellShowDTO.setDate(this.getDate());
        sellShowDTO.setClientName(clientName);
        sellShowDTO.setClientDNI(clientDNI);
        sellShowDTO.setClientRUC(clientRUC);
        sellShowDTO.setTotal(this.getTotal());
        sellShowDTO.setTotalVirtual(this.getTotalVirtual());
        sellShowDTO.setTotalPhisical(this.getTotalPhisical());
        sellShowDTO.setSellerNickname(this.getSeller().getNickname());
        return sellShowDTO;
    }

    public void calculateTotal(List<PayMethod> payMethods) {
    }

    public void calculateTotalPhisical(List<PayMethod> payMethods) {
    }

    public void calculateTotalVirtual(List<PayMethod> payMethods) {
    }
}
