package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.brand.Brand;
import com.two_weeks_backend.two_weeks_backend.entities.company.Company;
import com.two_weeks_backend.two_weeks_backend.entities.product.Product;
import com.two_weeks_backend.two_weeks_backend.entities.size.Size;
import com.two_weeks_backend.two_weeks_backend.entities.type.Type;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class ProductCreateDTO extends BaseCreateDTO<Product> {
    @NotNull
    @Min(0)
    private float sellPriceNormal;

    @NotNull
    @Min(0)
    private float sellPriceAuction;

    @NotNull
    @Min(0)
    private float sellPriceWholesale;

    @NotNull
    @Min(0)
    private float sellPriceWholesaleSuper;

    @NotNull
    private Long typeId;

    @NotNull
    private Long brandId;

    @NotNull
    private Long sizeId;

    @NotNull
    private Long companyId;

    @Override
    public Product asEntity() {
        Product product = new Product();
        product.setStock(0);
        product.setSellPriceNormal(this.getSellPriceNormal());
        product.setSellPriceAuction(this.getSellPriceAuction());
        product.setSellPriceWholesale(this.getSellPriceWholesale());
        product.setSellPriceWholesaleSuper(this.getSellPriceWholesaleSuper());
        product.setType(this.getTypeEntity());
        product.setBrand(this.getBrandEntity());
        product.setSize(this.getSizeEntity());
        product.setCompany(this.getCompanyEntity());
        return product;
    }

    private Type getTypeEntity() {
        if (this.getTypeId() != null) {
            Type type = new Type();
            type.setId(this.getTypeId());
            return type;
        }
        return null;
    }

    private Brand getBrandEntity() {
        if (this.getBrandId() != null) {
            Brand brand = new Brand();
            brand.setId(this.getBrandId());
            return brand;
        }
        return null;
    }

    private Size getSizeEntity() {
        if (this.getSizeId() != null) {
            Size size = new Size();
            size.setId(this.getSizeId());
            return size;
        }
        return null;
    }

    private Company getCompanyEntity() {
        if (this.getCompanyId() != null) {
            Company company = new Company();
            company.setId(this.getCompanyId());
            return company;
        }
        return null;
    }
}
