package com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.size;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Size;

import lombok.*;

@Getter
@Setter
public class SizeActivatedDTO extends BaseActivatedDTO<Size> {
    @Override
    public Size asEntity() {
        Size size = new Size();
        size.setId(this.getId());
        size.setActivated(this.getActivated());
        return size;
    }
}
