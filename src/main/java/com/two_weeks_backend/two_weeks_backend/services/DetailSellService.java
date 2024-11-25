package com.two_weeks_backend.two_weeks_backend.services;

import com.two_weeks_backend.two_weeks_backend.entities.*;
import com.two_weeks_backend.two_weeks_backend.repositories.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailSellService extends BaseServiceImplementation<DetailSell>{
    @Autowired
    private SellRepository sellRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public DetailSell create(DetailSell detailSell){
        DetailSell savedDetailSell = super.create(detailSell);
        savedDetailSell.setSell(this.loadSell(savedDetailSell.getSell().getId()));
        savedDetailSell.setProduct(this.loadProduct(savedDetailSell.getProduct().getId()));
        return savedDetailSell;
    }

    private Sell loadSell(Long sellId){
        return sellRepository.findById(sellId)
            .orElseThrow(() -> new RuntimeException("sell not found"));
    }
    private Product loadProduct(Long productId){
        return productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("product not found"));
    }
}
