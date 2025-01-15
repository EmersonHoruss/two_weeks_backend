package com.two_weeks_backend.two_weeks_backend.services.sell;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail.SellDetailCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.PriceLimit;
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

        if (priceType == PriceType.NORMAL)
            this.validateNormalPrice(sellDetailDTO, product);

        if (priceType == PriceType.WHOLESALE)
            this.validateWholesalePrice(sellDetailDTO, product);

        if (priceType == PriceType.SUPER_WHOLESAE)
            this.validateSuperWholesalePrice(sellDetailDTO, product);
    }

    private void validateNormalPrice(SellDetailCreateDTO sellDetailDTO, Product product) {
        float subTotal = sellDetailDTO.getSubTotal();
        int amount = sellDetailDTO.getAmount();

        if (!(amount >= PriceLimit.NORMAL_FROM.getValue() && amount <= PriceLimit.NORMAL_TO.getValue()))
            throw new RuntimeException("El tipo de precio normal debe tener una cantidad entre: "
                    + PriceLimit.NORMAL_FROM + " hasta " + PriceLimit.NORMAL_TO);

        if (!(product.getSellPriceNormal() * amount == subTotal))
            throw new RuntimeException(
                    "El subtotal no concuerda con el tipo de precio normal y la cantidad del producto");
    }

    private void validateWholesalePrice(SellDetailCreateDTO sellDetailDTO, Product product) {
        float subTotal = sellDetailDTO.getSubTotal();
        int amount = sellDetailDTO.getAmount();

        if (!(amount >= PriceLimit.WHOLESALE_FROM.getValue() && amount <= PriceLimit.WHOLESALE_TO.getValue()))
            throw new RuntimeException("El tipo de precio al por mayor debe tener una cantidad entre: "
                    + PriceLimit.WHOLESALE_FROM + " hasta " + PriceLimit.WHOLESALE_TO);

        if (!(product.getSellPriceWholesale() * amount == subTotal))
            throw new RuntimeException(
                    "El subtotal no concuerda con el tipo de precio al por mayor y la cantidad del producto");
    }

    private void validateSuperWholesalePrice(SellDetailCreateDTO sellDetailDTO, Product product) {
        float subTotal = sellDetailDTO.getSubTotal();
        int amount = sellDetailDTO.getAmount();

        if (!(amount >= PriceLimit.SUPER_WHOLESAE_FROM.getValue()))
            throw new RuntimeException("El tipo de precio super mayor debe tener una cantidad entre: "
                    + PriceLimit.SUPER_WHOLESAE_FROM + " hasta todos los que existan");

        if (!(product.getSellPriceWholesaleSuper() * amount == subTotal))
            throw new RuntimeException(
                    "El subtotal no concuerda con el tipo de precio al súper por mayor y la cantidad del producto");
    }
}
