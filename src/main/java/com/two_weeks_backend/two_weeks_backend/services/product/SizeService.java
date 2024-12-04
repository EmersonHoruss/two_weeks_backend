package com.two_weeks_backend.two_weeks_backend.services.product;

import org.springframework.stereotype.Service;

import com.two_weeks_backend.two_weeks_backend.entities.product.Size;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;

@Service
public class SizeService extends BaseServiceImplementation<Size> {
    @Override
    public Size create(Size size) {
        Size savedSize = super.create(size);
        return savedSize;
    }
}
