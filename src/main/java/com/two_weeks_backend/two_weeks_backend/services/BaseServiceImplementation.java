package com.two_weeks_backend.two_weeks_backend.services;

import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.repositories.BaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("rawtypes")
public abstract class BaseServiceImplementation<E extends BaseEntity> {
    @Autowired
    protected BaseRepository<E> baseRepository;

    @Transactional(rollbackFor = Exception.class)
    public E create(E entity) {
        return baseRepository.save(entity);
    }

    public E get(Long id) {
        return baseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public Page<E> get(Specification<E> specification, Pageable pageable) {
        return baseRepository.findAll(specification, pageable);
    }

    @Transactional(rollbackFor = Exception.class)
    public E update(E entity) {
        baseRepository.getReferenceById(entity.getId());
        return baseRepository.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        E entity = baseRepository.getReferenceById(id);
        baseRepository.delete(entity);
    }
}
