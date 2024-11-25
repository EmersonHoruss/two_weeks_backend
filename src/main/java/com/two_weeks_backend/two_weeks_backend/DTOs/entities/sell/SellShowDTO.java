package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.UserShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.customer.CustomerShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.*;
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
}
