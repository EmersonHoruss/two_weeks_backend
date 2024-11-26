package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.customer;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerShowDTO extends BaseShowDTO<Customer>{
    private String identity;
    private String firstName;
    private String surname;
    private String secondName;
    private String secondSurname;
    private String fullName;
    private boolean activated;
}
