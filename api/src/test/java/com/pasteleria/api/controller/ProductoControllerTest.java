package com.pasteleria.api.controller;

import com.pasteleria.api.entity.Producto;
import com.pasteleria.api.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = Producto.builder()
                .id(1L)
                .codigoProducto("PROD001")
                .nombreProducto("Producto Test")
                .precioProducto(new BigDecimal("99.99"))
                .descripcionProducto("Descripción de prueba")
                .stock(50)
                .stockCritico(10)
                .build();
    }

    @Test
    void testFindAll() throws Exception {
        List<Producto> productos = Arrays.asList(producto);
        when(productoService.findAll()).thenReturn(productos);

        mockMvc.perform(get("/api/productos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombreProducto").value("Producto Test"));
    }

    @Test
    void testFindById() throws Exception {
        when(productoService.findById(1L)).thenReturn(producto);

        mockMvc.perform(get("/api/productos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreProducto").value("Producto Test"));
    }

    @Test
    void testFindByCodigoProducto() throws Exception {
        when(productoService.findByCodigoProducto("PROD001")).thenReturn(producto);

        mockMvc.perform(get("/api/productos/codigo/PROD001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigoProducto").value("PROD001"));
    }

    @Test
    void testFindByCategoriaId() throws Exception {
        List<Producto> productos = Arrays.asList(producto);
        when(productoService.findByCategoriaId(1L)).thenReturn(productos);

        mockMvc.perform(get("/api/productos/categoria/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }
}
