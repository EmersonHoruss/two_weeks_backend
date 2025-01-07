package com.two_weeks_backend.two_weeks_backend.services.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;
import com.two_weeks_backend.two_weeks_backend.entities.purchase.ProductProvider;
import com.two_weeks_backend.two_weeks_backend.entities.purchase.Provider;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.services.catalog.ProductService;
import com.two_weeks_backend.two_weeks_backend.utils.specification.Specification;

@Service
public class ProductProviderService extends BaseServiceImplementation<ProductProvider> {
    @Autowired
    private ProviderService providerService;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductProvider create(ProductProvider productProvider) {
        Long productId = productProvider.getProduct().getId();
        Long providerId = productProvider.getProvider().getId();

        ProductProvider foundProductProvider = this.getProductProvider(productId, providerId);
        if (foundProductProvider != null)
            throw new RuntimeException(
                    "Ya existe el producto en el proveedor " + foundProductProvider.getProduct().getType().getName()
                            + " " + foundProductProvider.getProduct().getBrand().getName() + " "
                            + foundProductProvider.getProduct().getSize().getName());

        Product foundProduct = this.productService.get(providerId);
        if (foundProduct == null)
            throw new RuntimeException("Producto no encontrado");

        Provider foundProvider = this.providerService.get(providerId);
        if (foundProvider == null)
            throw new RuntimeException("Proveedor no encontrado");

        return this.baseRepository.save(productProvider);
    }

    private ProductProvider getProductProvider(Long productId, Long providerId) {
        String productAttribute = "product.id";
        String productOperator = "<eq>";
        String productQuery = productAttribute + productOperator + productId;

        String providerAttribute = "provider.id";
        String providerOperator = "<eq>";
        String providerQuery = providerAttribute + providerOperator + providerId;

        String connector = ":and:";
        String query = productQuery + connector + providerQuery;
        Specification<ProductProvider> specification = new Specification<>(query);

        Pageable pageable = PageRequest.of(0, 1);

        Page<ProductProvider> page = super.get(specification, pageable);

        return page.isEmpty() ? null : page.getContent().get(0);
    }

    public List<ProductProvider> getProductProvider(Long productId) {
        String productAttribute = "product.id";
        String productOperator = "<eq>";
        String productQuery = productAttribute + productOperator + productId;

        Specification<ProductProvider> specification = new Specification<>(productQuery);

        return this.baseRepository.findAll(specification);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductProvider update(ProductProvider productProvider) {
        ProductProvider retrievedProductProvider = this.baseRepository.getReferenceById(productProvider.getId());
        if (retrievedProductProvider == null)
            throw new RuntimeException("No se encontró producto del proveedor");

        if (!retrievedProductProvider.getActivated() || !retrievedProductProvider.getProduct().getActivated())
            throw new RuntimeException("El producto del proveedor o del cliente está eliminado");

        retrievedProductProvider.setPrice(productProvider.getPrice());
        return this.baseRepository.save(retrievedProductProvider);
    }

    @Transactional(rollbackFor = Exception.class)
    public ProductProvider setActivation(ProductProvider productProvider) {
        ProductProvider retrievedProductProvider = this.baseRepository.getReferenceById(productProvider.getId());
        if (retrievedProductProvider == null)
            throw new RuntimeException("No se encontró producto del proveedor");

        if (productProvider.getActivated() == retrievedProductProvider.getActivated())
            return retrievedProductProvider;

        retrievedProductProvider.setActivated(productProvider.getActivated());
        return this.baseRepository.save(retrievedProductProvider);
    }
}
