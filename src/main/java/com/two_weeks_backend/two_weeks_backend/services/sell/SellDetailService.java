package com.two_weeks_backend.two_weeks_backend.services.sell;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail.SellDetailCreateDTO;
import com.two_weeks_backend.two_weeks_backend.repositories.sell.SellDetailRepository;

@Service
public class SellDetailService {
    @Autowired
    private SellDetailRepository sellDetailRepository;

    public void create(List<SellDetailCreateDTO> sellDetails) {

    }
}
