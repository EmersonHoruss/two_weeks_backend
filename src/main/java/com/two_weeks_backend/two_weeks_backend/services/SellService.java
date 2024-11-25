package com.two_weeks_backend.two_weeks_backend.services;

import com.two_weeks_backend.two_weeks_backend.entities.*;
import com.two_weeks_backend.two_weeks_backend.repositories.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellService extends BaseServiceImplementation<Sell>{
    @Autowired
    private UserRepository sellerRepository;
    @Autowired
    private UserRepository debtCollectorRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public Sell create(Sell sell){
        Sell savedSell = super.create(sell);
        savedSell.setSeller(this.loadSeller(savedSell.getSeller().getId()));
        savedSell.setDebtCollector(this.loadDebtCollector(savedSell.getDebtCollector().getId()));
        savedSell.setCustomer(this.loadCustomer(savedSell.getCustomer().getId()));
        return savedSell;
    }

    private User loadSeller(Long userId){
        return sellerRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("seller not found"));
    }
    private User loadDebtCollector(Long userId){
        return debtCollectorRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("debtCollector not found"));
    }
    private Customer loadCustomer(Long customerId){
        return customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("customer not found"));
    }
}
