package com.two_weeks_backend.two_weeks_backend.services;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_compra.DetalleCompraCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_compra.DetalleCompraShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_compra.DetalleCompraUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.CompraEntity;
import com.two_weeks_backend.two_weeks_backend.entities.DetalleCompraEntity;
import com.two_weeks_backend.two_weeks_backend.entities.ProductoEntity;
import com.two_weeks_backend.two_weeks_backend.repositories.DetalleCompraRepository;

@Service
public class DetalleCompraService extends BaseServiceImplementation<DetalleCompraEntity> {
    @Autowired
    DetalleCompraRepository detalleCompraRepository;

    @Autowired
    ProductoService productoService;

    public void saveAll(CompraEntity compra, List<DetalleCompraCreateDTO> detalles, OffsetDateTime fechaCreacion) {
        Set<Long> productoIds = detalles.stream().map(DetalleCompraCreateDTO::getProductoId)
                .collect(Collectors.toSet());
        List<ProductoEntity> productos = productoService.areTheyOperative(productoIds);
        for (ProductoEntity productoEntity : productos) {
            if (!productoEntity.getDistribuidor().getId().equals(compra.getDistribuidor().getId())) {
                throw new RuntimeException(
                        "El producto " + productoEntity.getNombre() + " no pertence al distribuidor seleccionado.");
            }
        }

        List<DetalleCompraEntity> detallesCompraEntity = detalles.stream().map(detalleDTO -> {
            this.validate(detalleDTO);

            DetalleCompraEntity detalleCompraEntity = detalleDTO.asEntity();

            detalleCompraEntity.calculateSubTotal();
            detalleCompraEntity.setCompra(compra);
            detalleCompraEntity.setFechaCreacion(fechaCreacion);

            return detalleCompraEntity;
        }).toList();

        this.baseRepository.saveAll(detallesCompraEntity);
    }

    public BigDecimal saveAllAndGetTotal(CompraEntity compra, List<DetalleCompraUpdateDTO> detalles,
            OffsetDateTime fechaActualizacion) {
        Set<Long> productoIds = detalles.stream().map(DetalleCompraUpdateDTO::getProductoId)
                .collect(Collectors.toSet());
        List<ProductoEntity> productos = productoService.areTheyOperative(productoIds);
        for (ProductoEntity productoEntity : productos) {
            if (!productoEntity.getDistribuidor().getId().equals(compra.getDistribuidor().getId())) {
                throw new RuntimeException(
                        "El producto " + productoEntity.getNombre() + " no pertence al distribuidor seleccionado.");
            }
        }

        Long compraId = compra.getId();
        List<DetalleCompraEntity> currentDetalles = this.detalleCompraRepository.findAllByCompra_Id(compraId);

        Map<Long, DetalleCompraEntity> currentDetallesMap = currentDetalles.stream()
                .collect(Collectors.toMap(DetalleCompraEntity::getId, Function.identity()));

        List<DetalleCompraUpdateDTO> news = new ArrayList<>();
        List<DetalleCompraUpdateDTO> updateables = new ArrayList<>();

        for (DetalleCompraUpdateDTO dto : detalles) {
            if (dto.getId() == null) {
                news.add(dto);
            } else {
                updateables.add(dto);
            }
        }

        List<Long> updatablesIds = updateables.stream().map(DetalleCompraUpdateDTO::getId).toList();

        for (Long id : updatablesIds) {
            if (!currentDetallesMap.containsKey(id)) {
                throw new IllegalArgumentException("El detalle con ID " + id + " no pertenece a esta compra.");
            }
        }

        List<DetalleCompraEntity> detallesToUpdate = updateables.stream().map(dto -> {
            DetalleCompraEntity currentDetalle = currentDetallesMap.get(dto.getId());

            currentDetalle.setCantidad(dto.getCantidad());
            currentDetalle.setPrecioCompraUnitario(dto.getPrecioCompraUnitario());
            currentDetalle
                    .setActivated(dto.getActivated() != null ? dto.getActivated() : currentDetalle.getActivated());
            currentDetalle.setFechaActualizacion(fechaActualizacion);

            ProductoEntity producto = new ProductoEntity();
            producto.setId(dto.getProductoId());
            currentDetalle.setProducto(producto);

            currentDetalle.calculateSubTotal();

            return currentDetalle;
        }).toList();

        List<DetalleCompraEntity> detallesToCreate = news.stream().map(dto -> {
            DetalleCompraEntity detalleToCreate = dto.asEntity();
            detalleToCreate.setCompra(compra);
            detalleToCreate.calculateSubTotal();
            detalleToCreate.setFechaCreacion(fechaActualizacion);

            return detalleToCreate;
        }).toList();

        List<DetalleCompraEntity> allToSave = new ArrayList<>();
        allToSave.addAll(detallesToUpdate);
        allToSave.addAll(detallesToCreate);

        baseRepository.saveAll(allToSave);

        BigDecimal total = currentDetalles.stream().filter(detalle -> !updatablesIds.contains(detalle.getId()))
                .filter(DetalleCompraEntity::getActivated).map(DetalleCompraEntity::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(allToSave.stream().map(DetalleCompraEntity::getSubTotal).reduce(BigDecimal.ZERO, BigDecimal::add));

        return total;
    }

    private void validate(DetalleCompraCreateDTO detalleCompraCreateDTO) {
        Long productoId = detalleCompraCreateDTO.getProductoId();
        this.productoService.isItOperative(productoId);
    }

    public List<DetalleCompraShowDTO> getAllByCompraId(Long id) {
        List<DetalleCompraEntity> detallesEntity = this.detalleCompraRepository.findAllByCompra_Id(id);

        return detallesEntity.stream().map(DetalleCompraEntity::asShowDTO).toList();
    }

    public void setArrived(Long compraId, Boolean arrived) {
        List<DetalleCompraEntity> currentDetalles = this.detalleCompraRepository.findAllByCompra_Id(compraId);

        List<ProductoEntity> productos = arrived ? this.insertProducts(currentDetalles)
                : this.rollbackProducts(compraId, currentDetalles);

        this.productoService.saveAll(productos);
    }

    private List<ProductoEntity> insertProducts(List<DetalleCompraEntity> detallesCompra) {
        return detallesCompra.stream().filter(DetalleCompraEntity::isActivo).map(detalle -> {
            ProductoEntity producto = detalle.getProducto();
            producto.setPrecioCompra(detalle.getPrecioCompraUnitario());
            producto.addStock(detalle.getCantidad());
            return producto;
        }).collect(Collectors.toList());
    }

    private List<ProductoEntity> rollbackProducts(Long compraId, List<DetalleCompraEntity> detallesCompra) {
        List<Long> productoIds = detallesCompra.stream().filter(DetalleCompraEntity::isActivo)
                .map(detalle -> detalle.getProducto().getId()).toList();

        List<DetalleCompraEntity> detallesPrevios = this.detalleCompraRepository
                .findMostRecentArrivedDetallesByProductoIdsExcludingCompra(productoIds, compraId);

        Map<Long, DetalleCompraEntity> detallesPreviosMap = detallesPrevios.stream()
                .collect(Collectors.toMap(detalle -> detalle.getProducto().getId(), Function.identity()));

        return detallesCompra.stream().filter(DetalleCompraEntity::isActivo).map(detalle -> {
            ProductoEntity producto = detalle.getProducto();
            int cantidad = detalle.getCantidad();

            DetalleCompraEntity previo = detallesPreviosMap.get(producto.getId());
            if (previo != null) {
                producto.setPrecioCompra(previo.getPrecioCompraUnitario());
            }

            producto.reduceStock(cantidad);
            return producto;
        }).collect(Collectors.toList());
    }
}
