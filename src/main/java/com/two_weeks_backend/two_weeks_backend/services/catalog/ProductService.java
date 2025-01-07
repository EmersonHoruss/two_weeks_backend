package com.two_weeks_backend.two_weeks_backend.services.catalog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;
import com.two_weeks_backend.two_weeks_backend.entities.company.ProductCompany;
import com.two_weeks_backend.two_weeks_backend.entities.purchase.ProductProvider;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.services.company.ProductCompanyService;
import com.two_weeks_backend.two_weeks_backend.services.purchase.ProductProviderService;
import com.two_weeks_backend.two_weeks_backend.utils.specification.Specification;

@Service
public class ProductService extends BaseServiceImplementation<Product> {
    @Autowired
    private ProductCompanyService productCompanyService;

    @Autowired
    private ProductProviderService productProviderService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Product create(Product product) {
        String nameInTenant = product.getNameInTenant();

        Product foundProduct = this.getProduct(nameInTenant);
        if (foundProduct != null) {
            String exceptionMessage = "Ya existe el producto con el tipo " + foundProduct.getType().getName()
                    + ", la marca " + foundProduct.getBrand().getName() + ", y la talla "
                    + foundProduct.getSize().getName();
            throw new RuntimeException(exceptionMessage);
        }

        return this.baseRepository.save(product);
    }

    private Product getProduct(String nameInTenant) {
        String attribute = "nameInTenant";
        String operator = "<eq>";
        String query = attribute + operator + nameInTenant;
        Specification<Product> specification = new Specification<>(query);

        Pageable pageable = PageRequest.of(0, 1);

        Page<Product> page = super.get(specification, pageable);

        if (page.isEmpty())
            return null;

        return page.getContent().get(0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Product update(Product product) {
        Product retrievedProduct = this.baseRepository.getReferenceById(product.getId());

        if (retrievedProduct == null)
            throw new RuntimeException("No se ha encontrado el producto");

        if (!retrievedProduct.getActivated())
            throw new RuntimeException("El producto está eliminado");

        if (product.getSellPriceNormal() == retrievedProduct.getSellPriceNormal()
                && product.getSellPriceAuction() == retrievedProduct.getSellPriceAuction()
                && product.getSellPriceWholesale() == retrievedProduct.getSellPriceWholesale()
                && product.getSellPriceWholesaleSuper() == retrievedProduct.getSellPriceWholesaleSuper())
            return retrievedProduct;

        retrievedProduct.setSellPriceNormal(product.getSellPriceNormal());
        retrievedProduct.setSellPriceAuction(product.getSellPriceAuction());
        retrievedProduct.setSellPriceWholesale(product.getSellPriceWholesale());
        retrievedProduct.setSellPriceWholesaleSuper(product.getSellPriceWholesaleSuper());
        return this.baseRepository.save(retrievedProduct);
    }

    @Transactional(rollbackFor = Exception.class)
    public Product setActivation(Product product) {
        Product retrievedProduct = this.baseRepository.getReferenceById(product.getId());

        if (retrievedProduct == null)
            throw new RuntimeException("No se ha encontrado el producto");

        if (product.getActivated() == retrievedProduct.getActivated())
            return retrievedProduct;

        Long productId = product.getId();
        List<ProductCompany> productCompanies = this.productCompanyService.getProductCompany(productId);
        for (ProductCompany productCompany : productCompanies) {
            if (!productCompany.getActivated())
                continue;

            productCompany.setActivated(false);
            this.productCompanyService.setActivation(productCompany);
        }

        this.productProviderService.getProductProvider(productId);
        List<ProductProvider> productProviders = this.productProviderService.getProductProvider(productId);
        for (ProductProvider productProvider : productProviders) {
            if (!productProvider.getActivated())
                continue;

            productProvider.setActivated(false);
            this.productProviderService.setActivation(productProvider);
        }

        retrievedProduct.setActivated(product.getActivated());
        return this.baseRepository.save(retrievedProduct);
    }
}
