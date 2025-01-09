package com.two_weeks_backend.two_weeks_backend.services.company;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;
import com.two_weeks_backend.two_weeks_backend.entities.company.Company;
import com.two_weeks_backend.two_weeks_backend.entities.company.ProductCompany;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.services.catalog.ProductService;
import com.two_weeks_backend.two_weeks_backend.utils.specification.Specification;

@Service
public class ProductCompanyService extends BaseServiceImplementation<ProductCompany> {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductCompany create(ProductCompany productCompany) {
        Long productId = productCompany.getProduct().getId();
        Long companyId = productCompany.getCompany().getId();

        ProductCompany foundProductCompany = this.getProductCompany(productId, companyId);
        if (foundProductCompany != null)
            throw new RuntimeException(
                    "Ya existe el producto en la empresa " + foundProductCompany.getCompany().getName());

        Product foundProduct = this.productService.get(productId);
        if (foundProduct == null)
            throw new RuntimeException("Producto no encontrado");

        Company foundCompany = this.companyService.get(companyId);
        if (foundCompany == null)
            throw new RuntimeException("Empresa no encontrada para agregar el producto");

        return this.baseRepository.save(productCompany);
    }

    private ProductCompany getProductCompany(Long productId, Long companyId) {
        String productAttribute = "product.id";
        String productOperator = "<eq>";
        String productQuery = productAttribute + productOperator + productId;

        String companyAttribute = "company.id";
        String companyOperator = "<eq>";
        String companyQuery = companyAttribute + companyOperator + companyId;

        String connector = ":and:";
        String query = productQuery + connector + companyQuery;
        Specification<ProductCompany> specification = new Specification<>(query);

        Pageable pageable = PageRequest.of(0, 1);

        Page<ProductCompany> page = super.get(specification, pageable);

        return page.isEmpty() ? null : page.getContent().get(0);
    }

    public List<ProductCompany> getProductCompany(Long productId) {
        String productAttribute = "product.id";
        String productOperator = "<eq>";
        String productQuery = productAttribute + productOperator + productId;

        Specification<ProductCompany> specification = new Specification<>(productQuery);

        return this.baseRepository.findAll(specification);
    }

    @Override
    public ProductCompany update(ProductCompany productCompany) {
        throw new UnsupportedOperationException("No se permite la actualización del producto de una empresa");
    }

    public ProductCompany addStock(Long productCompanyId, int amount) {
        ProductCompany retrievedProductCompany = this.baseRepository.getReferenceById(productCompanyId);

        if (retrievedProductCompany == null)
            throw new RuntimeException("No se ha encontrado el producto de la empresa solicitado");

        if (!retrievedProductCompany.getActivated() || !retrievedProductCompany.getProduct().getActivated())
            throw new RuntimeException("El producto de la empresa o del cliente está eliminado");

        retrievedProductCompany.addStock(amount);
        return this.baseRepository.save(retrievedProductCompany);
    }

    public ProductCompany subtractStock(Long productCompanyId, int amount) {
        ProductCompany retrievedProductCompany = this.baseRepository.getReferenceById(productCompanyId);

        if (retrievedProductCompany == null)
            throw new RuntimeException("No se ha encontrado el producto de la empresa solicitado");

        if (!retrievedProductCompany.getActivated() || !retrievedProductCompany.getProduct().getActivated())
            throw new RuntimeException("El producto de la empresa o del cliente está eliminado");

        if (amount > retrievedProductCompany.getStock()) {
            String errorMesasge = String.format(
                    "Stock insuficiente: Se intentó solicitar %d unidades, pero solo hay %d unidades disponibles.",
                    amount, retrievedProductCompany.getStock());
            throw new RuntimeException(errorMesasge);
        }

        retrievedProductCompany.subtractStock(amount);
        return this.baseRepository.save(retrievedProductCompany);
    }

    @Transactional(rollbackFor = Exception.class)
    public ProductCompany setActivation(ProductCompany productCompany) {
        ProductCompany retrievedProductCompany = this.baseRepository.getReferenceById(productCompany.getId());

        if (retrievedProductCompany == null)
            throw new RuntimeException("No se ha encontrado el producto de la empresa");

        if (productCompany.getActivated() == retrievedProductCompany.getActivated())
            return retrievedProductCompany;

        return this.baseRepository.save(retrievedProductCompany);
    }
}
