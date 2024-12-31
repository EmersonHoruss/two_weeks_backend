package com.two_weeks_backend.two_weeks_backend.services.point_of_sale;

import com.two_weeks_backend.two_weeks_backend.entities.point_of_sale.PointOfSale;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PointOfSaleService extends BaseServiceImplementation<PointOfSale> {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PointOfSale create(PointOfSale pointOfSale) {
        PointOfSale savedPointOfSale = super.create(pointOfSale);
        return savedPointOfSale;
    }

}
