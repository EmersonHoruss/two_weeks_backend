package com.two_weeks_backend.two_weeks_backend.services;

import com.two_weeks_backend.two_weeks_backend.entities.*;
import com.two_weeks_backend.two_weeks_backend.repositories.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
