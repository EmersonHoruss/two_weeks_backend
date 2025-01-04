package com.two_weeks_backend.two_weeks_backend.services.sell;

import com.two_weeks_backend.two_weeks_backend.entities.product.ProductSet;
import com.two_weeks_backend.two_weeks_backend.entities.sell.DetailSell;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;
import com.two_weeks_backend.two_weeks_backend.repositories.product.ProductSetRepository;
import com.two_weeks_backend.two_weeks_backend.repositories.sell.SellRepository;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailSellService extends BaseServiceImplementation<DetailSell> {
    @Autowired
    private SellRepository sellRepository;
    @Autowired
    private ProductSetRepository productSetRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DetailSell create(DetailSell detailSell) {
        DetailSell savedDetailSell = super.create(detailSell);
        savedDetailSell.setSell(this.loadSell(savedDetailSell.getSell().getId()));
        savedDetailSell.setProduct(this.loadProduct(savedDetailSell.getProduct().getId()));
        return savedDetailSell;
    }

    private Sell loadSell(Long sellId) {
        return sellRepository.findById(sellId)
                .orElseThrow(() -> new RuntimeException("sell not found"));
    }

    private ProductSet loadProduct(Long productId) {
        return productSetRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("product not found"));
    }
}
