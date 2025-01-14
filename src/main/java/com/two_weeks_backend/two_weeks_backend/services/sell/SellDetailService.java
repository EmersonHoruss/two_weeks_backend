package com.two_weeks_backend.two_weeks_backend.services.sell;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail.SellDetailCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.PriceType;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;
import com.two_weeks_backend.two_weeks_backend.entities.company.ProductCompany;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellDetail;
import com.two_weeks_backend.two_weeks_backend.repositories.sell.SellDetailRepository;
import com.two_weeks_backend.two_weeks_backend.services.catalog.ProductService;
import com.two_weeks_backend.two_weeks_backend.services.company.ProductCompanyService;

@Service
public class SellDetailService {
    @Autowired
    private SellDetailRepository sellDetailRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCompanyService productCompanyService;

    public List<SellDetail> create(List<SellDetailCreateDTO> sellDetailsCreateDTO, Long sellId, Long companyId) {
        if (sellId == null)
            throw new RuntimeException("Para crear los detalles de venta debe existir la venta");
        Sell sell = new Sell();
        sell.setId(sellId);

        List<SellDetail> savedSellDetail = sellDetailsCreateDTO.stream().map(sellDetailDTO -> {
            Product product = this.productService.validate(sellDetailDTO.getProductId());
            ProductCompany productCompany = this.productCompanyService.validate(product.getId(), companyId);
            Long productCompanyId = productCompany.getId();

            this.validatePrice(sellDetailDTO, product);

            int amount = sellDetailDTO.getAmount();
            this.productCompanyService.subtractStock(productCompanyId, amount);

            SellDetail sellDetail = sellDetailDTO.asEntity();
            sellDetail.setSell(sell);
            this.sellDetailRepository.save(sellDetail);

            return sellDetail;
        }).collect(Collectors.toList());

        return savedSellDetail;
    }

    private void validatePrice(SellDetailCreateDTO sellDetailDTO, Product product) {
        PriceType priceType = sellDetailDTO.getPriceType();
        if (priceType == PriceType.AUCTION || priceType == PriceType.PREFERENCE)
            return;

        float subTotal = sellDetailDTO.getSubTotal();
        if (priceType == PriceType.NORMAL) this.validateNormalPrice();

        if(priceType==PriceType.)
    }

    private void validateNormalPrice() {
    }

    private void validateWholesalePrice() {
    }

    private void validateSuperWholesalePrice() {
    }
}
