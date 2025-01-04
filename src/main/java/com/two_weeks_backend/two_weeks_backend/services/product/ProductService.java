package com.two_weeks_backend.two_weeks_backend.services.product;

import com.two_weeks_backend.two_weeks_backend.entities.product.BarCode;
import com.two_weeks_backend.two_weeks_backend.entities.product.Product;
import com.two_weeks_backend.two_weeks_backend.exceptions.NotAllowed;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.utils.specification.Specification;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@Service
public class ProductService extends BaseServiceImplementation<Product> {
    @Autowired
    private BarCodeService barCodeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Product create(Product product) {
        Long typeId = product.getType().getId();
        Long brandId = product.getBrand().getId();
        Long sizeId = product.getSize().getId();
        if (this.isAlreadyCreated(typeId, brandId, sizeId))
            throw new NotAllowed("El producto ya ha sido creado");

        BarCode barCode = this.barCodeService.getByCompanyId(product.getCompany().getId());
        if (barCode == null)
            throw new RuntimeException("No se encontró código de barras para la compañía");

        String lastConsecutiveBarCode = barCode.getLastConsecutiveBarCode();
        product.setCode(lastConsecutiveBarCode);
        barCode.generateLastConsecutiveBarCode();

        this.barCodeService.update(barCode);

        Product savedProduct = super.create(product);
        return savedProduct;
    }

    private boolean isAlreadyCreated(Long typeId, Long brandId, Long sizeId) {
        String query = "type.id<eq>" + typeId + ":and:" +
                "brand.id<eq>" + brandId + ":and:" +
                "size.id<eq>" + sizeId;
        Specification<Product> specification = new Specification<Product>(query);

        Pageable pageable = PageRequest.of(0, 1);

        Page<Product> page = this.get(specification, pageable);
        return !page.isEmpty();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Product update(Product product) {
        Product retrievedProduct = this.baseRepository.getReferenceById(product.getId());
        if (!retrievedProduct.getActivated())
            throw new NotAllowed("Operación fallida: El producto está eliminado");

        if (retrievedProduct.getSellPriceNormal() == product.getSellPriceNormal() &&
                retrievedProduct.getSellPriceAuction() == product.getSellPriceAuction() &&
                retrievedProduct.getSellPriceWholesale() == product.getSellPriceWholesale() &&
                retrievedProduct.getSellPriceWholesaleSuper() == product.getSellPriceWholesaleSuper())
            return product;

        retrievedProduct.setSellPriceNormal(product.getSellPriceNormal());
        retrievedProduct.setSellPriceAuction(product.getSellPriceAuction());
        retrievedProduct.setSellPriceWholesale(product.getSellPriceWholesale());
        retrievedProduct.setSellPriceWholesaleSuper(product.getSellPriceWholesaleSuper());

        return this.baseRepository.save(retrievedProduct);
    }

    public Product setActivation(Product product) {
        Product retrievedProduct = this.baseRepository.getReferenceById(product.getId());

        if (retrievedProduct.getActivated() == product.getActivated())
            return product;

        retrievedProduct.setActivated(product.getActivated());

        return this.baseRepository.save(retrievedProduct);
    }
}
