package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;
import org.springframework.lang.Nullable;
import java.time.LocalDate;

@Getter
@Setter
public class SellCreateDTO extends BaseCreateDTO<Sell> {
    @Min(0)
    private float totalAmount;
    private LocalDate date;
    private String paymentMethod;
    private String status;
    @NotNull
    private Long seller;
    private Long debtCollector;
    private Long customer;

    @Override
    public Sell asEntity() {
        Sell sell = new Sell();
        sell.setTotalAmount(this.getTotalAmount());
        sell.setDate(this.getDate());
        sell.setPaymentMethod(this.getPaymentMethod());
        sell.setStatus(this.getStatus());
        sell.setSeller(this.getSellerEntity());
        sell.setDebtCollector(this.getDebtCollectorEntity());
        sell.setCustomer(this.getCustomerEntity());
        return sell;
    }

    private User getSellerEntity(){
        if(this.getSeller()!=null){
            User seller = new User();
            seller.setId(this.getSeller());
            return seller;
        }
        return null;
    }
    private User getDebtCollectorEntity(){
        if(this.getDebtCollector()!=null){
            User debtCollector = new User();
            debtCollector.setId(this.getDebtCollector());
            return debtCollector;
        }
        return null;
    }
    private Customer getCustomerEntity(){
        if(this.getCustomer()!=null){
            Customer customer = new Customer();
            customer.setId(this.getCustomer());
            return customer;
        }
        return null;
    }
}
