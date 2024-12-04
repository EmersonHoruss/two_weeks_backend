package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.size;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.product.Size;

import lombok.Getter;
import lombok.Setter;

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
