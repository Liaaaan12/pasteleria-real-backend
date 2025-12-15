package com.pasteleria.api.repository;

import com.pasteleria.api.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByCodigoProducto(String codigoProducto);
    List<Producto> findByCategoriaId(Long categoriaId);
}
