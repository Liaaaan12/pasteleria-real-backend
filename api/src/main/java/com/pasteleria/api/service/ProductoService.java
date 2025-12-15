package com.pasteleria.api.service;

import com.pasteleria.api.entity.Producto;
import com.pasteleria.api.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductoService {

    private final ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto findById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    public Producto findByCodigoProducto(String codigo) {
        return productoRepository.findByCodigoProducto(codigo)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con código: " + codigo));
    }

    public List<Producto> findByCategoriaId(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }

    @Transactional
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Transactional
    public Producto update(Long id, Producto producto) {
        Producto existente = findById(id);
        existente.setNombreProducto(producto.getNombreProducto());
        existente.setPrecioProducto(producto.getPrecioProducto());
        existente.setDescripcionProducto(producto.getDescripcionProducto());
        existente.setImagenProducto(producto.getImagenProducto());
        existente.setStock(producto.getStock());
        existente.setStockCritico(producto.getStockCritico());
        return productoRepository.save(existente);
    }

    @Transactional
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }
}
