package com.two_weeks_backend.two_weeks_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_compra.DetalleCompraCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_compra.DetalleCompraShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.CompraEntity;
import com.two_weeks_backend.two_weeks_backend.entities.DetalleCompraEntity;
import com.two_weeks_backend.two_weeks_backend.repositories.DetalleCompraRepository;

@Service
public class DetalleCompraService extends BaseServiceImplementation<DetalleCompraEntity> {
    @Autowired
    DetalleCompraRepository detalleCompraRepository;

    @Autowired
    ProductoService productoService;

    public void saveAll(CompraEntity compra, List<DetalleCompraCreateDTO> detalles) {
        List<DetalleCompraEntity> detallesCompraEntity = detalles.stream().map(detalleDTO -> {
            this.validate(detalleDTO);

            DetalleCompraEntity detalleCompraEntity = detalleDTO.asEntity();

            detalleCompraEntity.setCompra(compra);

            return detalleCompraEntity;
        }).toList();

        this.baseRepository.saveAll(detallesCompraEntity);
    }

    private void validate(DetalleCompraCreateDTO detalleCompraCreateDTO) {
        Long productoId = detalleCompraCreateDTO.getProductoId();
        this.productoService.isItOperative(productoId);
    }

    public List<DetalleCompraShowDTO> getAllByCompraId(Long id) {
        List<DetalleCompraEntity> detallesEntity = this.detalleCompraRepository.findAllByCompra_Id(id);

        return detallesEntity.stream().map(DetalleCompraEntity::asShowDTO).toList();
    }
}
