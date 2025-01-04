package com.two_weeks_backend.two_weeks_backend.services.type;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.entities.type.Type;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;

@Service
public class TypeService extends BaseServiceImplementation<Type> {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Type update(Type type) {
        Type retrievedType = baseRepository.getReferenceById(type.getId());

        if (type.getName().trim().equals(retrievedType.getName()))
            return retrievedType;

        retrievedType.setName(type.getName());

        return baseRepository.save(retrievedType);
    }

    @Transactional(rollbackFor = Exception.class)
    public Type setActivation(Type type) {
        Type retrievedType = baseRepository.getReferenceById(type.getId());

        if (type.getActivated() == retrievedType.getActivated())
            return retrievedType;

        retrievedType.setActivated(type.getActivated());

        return baseRepository.save(retrievedType);
    }
}
