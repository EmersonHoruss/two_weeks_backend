package com.two_weeks_backend.two_weeks_backend.DTOs.entities;

import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("rawtypes")
@Getter
@Setter
public abstract class BaseActivatedDTO<E extends BaseEntity> implements BaseDTO<E> {
    @NotNull
    private Long id;

    @NotNull
    private Boolean activated;
}
