package com.two_weeks_backend.two_weeks_backend.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.producto.ProductoActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.producto.ProductoCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.producto.ProductoUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.DistribuidorEntity;
import com.two_weeks_backend.two_weeks_backend.entities.ProductoEntity;
import com.two_weeks_backend.two_weeks_backend.repositories.ProductoRepository;

@Service
public class ProductoService extends BaseServiceImplementation<ProductoEntity> {
    @Autowired
    DistribuidorService distribuidorService;

    @Autowired
    ProductoRepository productoRepository;

    @Transactional(rollbackFor = Exception.class)
    public Long create(ProductoCreateDTO productoCreateDTO) {
        boolean exists = this.exists(productoCreateDTO);
        if (exists) {
            throw new RuntimeException("El producto ya existe.");
        }

        ProductoEntity productoEntity = productoCreateDTO.asEntity();

        return this.create(productoEntity);
    }

    private boolean exists(ProductoCreateDTO productoCreateDTO) {
        Long distribuidorId = productoCreateDTO.getDistribuidorId();
        DistribuidorEntity distribuidor = this.distribuidorService.get(distribuidorId);

        String distribuidorEmpresaNombre = distribuidor.getEmpresaNombre();
        String productoNombre = productoCreateDTO.getNombre();

        String nombreUnico = ProductoEntity.buildNombreUnico(productoNombre, distribuidorEmpresaNombre);

        return productoRepository.existsByNombreUnico(nombreUnico);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(ProductoUpdateDTO productoUpdateDTO) {
        ProductoEntity productoEntity = this.get(productoUpdateDTO.getId());

        validateIsActivated(productoEntity);

        productoEntity.setPrecioVentaMenor(productoUpdateDTO.getPrecioVentaMenor());
        productoEntity.setPrecioVentaMayor(productoUpdateDTO.getPrecioVentaMayor());
        productoEntity.setPrecioVentaSuperMayor(productoUpdateDTO.getPrecioVentaSuperMayor());

        this.update(productoEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void setActivated(ProductoActivatedDTO productoActivatedDTO) {
        ProductoEntity productoEntity = this.get(productoActivatedDTO.getId());

        productoEntity.setActivated(productoActivatedDTO.getActivated());

        this.update(productoEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        // for the moment to do not waste time i will avoid this feature
        throw new RuntimeException("Eliminación de producto no disponible.");
        // validar que no exista algun detalle de compra o de venta con ese id
        // validar que no esté activado, debe estar desactivado
    }

    public void isItOperative(Long id) {
        ProductoEntity producto = this.get(id);

        validateIsActivated(producto);
    }

    public void areTheyOperative(Set<Long> productoIds) {
        if (productoIds == null || productoIds.isEmpty()) {
            throw new IllegalArgumentException("Debe proporcionar al menos un ID de producto.");
        }

        List<ProductoEntity> productos = productoRepository.findAllById(productoIds);

        Set<Long> encontrados = productos.stream().map(ProductoEntity::getId).collect(Collectors.toSet());

        for (Long id : productoIds) {
            if (!encontrados.contains(id)) {
                throw new RuntimeException("El producto con ID " + id + " no existe.");
            }
        }

        for (ProductoEntity producto : productos) {
            validateIsActivated(producto);
        }
    }

    @Override
    public ProductoEntity get(Long id) {
        return baseRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public static void validateIsActivated(ProductoEntity producto) {
        Boolean isActivated = producto.getActivated();
        if (!isActivated) {
            throw new RuntimeException("El producto " + producto.getNombre() + " está desactivado");
        }
    }

    public void addStock(Long productId, int amount) {
        ProductoEntity productoEntity = this.get(productId);

        productoEntity.addStock(amount);

        this.update(productoEntity);
    }

    public void reduceStock(Long productId, int amount) {
        ProductoEntity productoEntity = this.get(productId);

        productoEntity.reduceStock(amount);

        this.update(productoEntity);
    }
}
