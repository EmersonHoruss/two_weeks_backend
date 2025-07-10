package com.two_weeks_backend.two_weeks_backend.DTOs.entities.producto;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.ProductoEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoActivatedDTO extends BaseActivatedDTO<ProductoEntity> {
    @Override
    public ProductoEntity asEntity() {
        ProductoEntity producto = new ProductoEntity();
        producto.setId(this.getId());
        producto.setActivated(this.getActivated());
        return producto;
    }
}
