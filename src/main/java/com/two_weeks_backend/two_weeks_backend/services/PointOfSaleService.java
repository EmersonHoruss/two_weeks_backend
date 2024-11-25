package com.two_weeks_backend.two_weeks_backend.services;

import com.two_weeks_backend.two_weeks_backend.entities.*;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PointOfSaleService extends BaseServiceImplementation<PointOfSale>{

    @Override
    @Transactional
    public PointOfSale create(PointOfSale pointOfSale){
        PointOfSale savedPointOfSale = super.create(pointOfSale);
        return savedPointOfSale;
    }

}
