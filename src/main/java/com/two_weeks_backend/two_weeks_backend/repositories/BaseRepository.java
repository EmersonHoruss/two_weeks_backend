package com.two_weeks_backend.two_weeks_backend.repositories;

import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@SuppressWarnings("rawtypes")
@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Long>, JpaSpecificationExecutor<E>{
}
