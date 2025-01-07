package com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.size;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Size;

import lombok.*;

@Getter
@Setter
public class SizeShowDTO extends BaseShowDTO<Size> {
    private String name;
    private boolean activated;
}
