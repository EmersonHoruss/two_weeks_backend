package com.two_weeks_backend.two_weeks_backend.services.size;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.entities.size.Size;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;

@Service
public class SizeService extends BaseServiceImplementation<Size> {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Size update(Size size) {
        Size retrievedSize = this.baseRepository.getReferenceById(size.getId());

        if (size.getName().trim().equals(retrievedSize.getName()))
            return retrievedSize;

        retrievedSize.setName(size.getName());

        return this.baseRepository.save(retrievedSize);
    }

    @Transactional(rollbackFor = Exception.class)
    public Size setActivation(Size size) {
        Size retrievedSize = this.baseRepository.getReferenceById(size.getId());

        if (size.getActivated() == retrievedSize.getActivated())
            return retrievedSize;

        retrievedSize.setActivated(size.getActivated());

        return this.baseRepository.save(retrievedSize);
    }
}
