package com.two_weeks_backend.two_weeks_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.two_weeks_backend.two_weeks_backend.entities.DetalleCompraEntity;

@Repository
public interface DetalleCompraRepository extends BaseRepository<DetalleCompraEntity> {
    List<DetalleCompraEntity> findAllByCompra_Id(Long compraId);

    @Query(value = """
            SELECT *
            FROM (
                SELECT d.*,
                       ROW_NUMBER() OVER (
                           PARTITION BY d.producto_id
                           ORDER BY COALESCE(d.fecha_actualizacion, d.fecha_creacion) DESC
                       ) AS rn
                FROM detalle_compra d
                JOIN compra c ON d.compra_id = c.id
                WHERE d.producto_id IN (:productoIds)
                  AND c.activated = true
                  AND c.llego = true
                  AND c.id <> :excludedCompraId
                  AND d.activated = true
            ) AS ranked
            WHERE ranked.rn = 1
            """, nativeQuery = true)
    List<DetalleCompraEntity> findMostRecentArrivedDetallesByProductoIdsExcludingCompra(
            @Param("productoIds") List<Long> productoIds, @Param("excludedCompraId") Long excludedCompraId);
}
