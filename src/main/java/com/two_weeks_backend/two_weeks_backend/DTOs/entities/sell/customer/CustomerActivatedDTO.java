package com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.customer;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerActivatedDTO extends BaseActivatedDTO<Customer> {
    @Override
    public Customer asEntity() {
        Customer customer = new Customer();
        customer.setId(this.getId());
        customer.setActivated(this.getActivated());
        return customer;
    }
}
