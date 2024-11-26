package com.two_weeks_backend.two_weeks_backend.services.sell;

import com.two_weeks_backend.two_weeks_backend.entities.sell.Customer;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends BaseServiceImplementation<Customer>{

    @Override
    @Transactional
    public Customer create(Customer customer){
        Customer savedCustomer = super.create(customer);
        return savedCustomer;
    }

}