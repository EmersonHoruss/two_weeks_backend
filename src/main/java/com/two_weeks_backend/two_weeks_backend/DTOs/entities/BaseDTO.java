package com.two_weeks_backend.two_weeks_backend.DTOs.entities;

import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;

public interface BaseDTO<E extends BaseEntity> {
    public E asEntity();
}
