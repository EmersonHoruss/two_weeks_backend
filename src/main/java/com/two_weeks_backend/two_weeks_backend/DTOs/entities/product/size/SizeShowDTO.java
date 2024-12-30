package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.size;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.product.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SizeShowDTO extends BaseShowDTO<Size> {
    private String name;
    private String code;
    private boolean activated;
}
