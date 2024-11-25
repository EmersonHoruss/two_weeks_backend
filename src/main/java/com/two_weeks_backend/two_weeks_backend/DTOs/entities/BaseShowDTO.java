package com.two_weeks_backend.two_weeks_backend.DTOs.entities;

import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("rawtypes")
@Getter
@Setter
public abstract class BaseShowDTO<E extends BaseEntity> {
    private Long id;
    private Boolean activated;
}
