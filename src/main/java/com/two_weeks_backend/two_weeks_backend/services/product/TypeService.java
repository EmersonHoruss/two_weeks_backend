package com.two_weeks_backend.two_weeks_backend.services.product;

import org.springframework.stereotype.Service;

import com.two_weeks_backend.two_weeks_backend.entities.product.Type;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;

@Service
public class TypeService extends BaseServiceImplementation<Type> {
    @Override
    public Type create(Type type) {
        Type savedType = super.create(type);
        return savedType;
    }
}
