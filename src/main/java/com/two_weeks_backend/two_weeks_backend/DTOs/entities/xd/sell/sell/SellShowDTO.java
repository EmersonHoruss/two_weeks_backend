package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.customer.CustomerShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.user.UserShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class SellShowDTO extends BaseShowDTO<Sell>{
    private float totalAmount;
    private LocalDate date;
    private String paymentMethod;
    private String status;
    private UserShowDTO seller;
    private UserShowDTO debtCollector;
    private CustomerShowDTO customer;
    private boolean activated;
}
