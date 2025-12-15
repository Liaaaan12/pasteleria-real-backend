package com.pasteleria.api.config;

import com.pasteleria.api.entity.Categoria;
import com.pasteleria.api.entity.Producto;
import com.pasteleria.api.entity.Usuario;
import com.pasteleria.api.repository.CategoriaRepository;
import com.pasteleria.api.repository.ProductoRepository;
import com.pasteleria.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (categoriaRepository.count() == 0) {
            createCategoriasYProductos();
        }
        if (usuarioRepository.count() == 0) {
            createUsuarios();
        }
        System.out.println(">>> Sistema iniciado con base de datos MySQL y datos de ejemplo.");
    }

    private void createCategoriasYProductos() {
        // Crear categorías
        Categoria pasteles = Categoria.builder()
                .nombreCategoria("Pasteles")
                .build();
        categoriaRepository.save(pasteles);

        Categoria galletas = Categoria.builder()
                .nombreCategoria("Galletas")
                .build();
        categoriaRepository.save(galletas);

        Categoria postres = Categoria.builder()
                .nombreCategoria("Postres")
                .build();
        categoriaRepository.save(postres);

        // Crear productos
        Producto producto1 = Producto.builder()
                .codigoProducto("PROD001")
                .nombreProducto("Pastel de Chocolate")
                .descripcionProducto("Delicioso pastel de chocolate con relleno de crema")
                .precioProducto(new BigDecimal("45.00"))
                .stock(10)
                .stockCritico(2)
                .categoria(pasteles)
                .build();
        productoRepository.save(producto1);

        Producto producto2 = Producto.builder()
                .codigoProducto("PROD002")
                .nombreProducto("Pastel de Vainilla")
                .descripcionProducto("Pastel clásico de vainilla")
                .precioProducto(new BigDecimal("40.00"))
                .stock(15)
                .stockCritico(2)
                .categoria(pasteles)
                .build();
        productoRepository.save(producto2);

        Producto producto3 = Producto.builder()
                .codigoProducto("PROD003")
                .nombreProducto("Galletas de Avena")
                .descripcionProducto("Galletas saludables de avena")
                .precioProducto(new BigDecimal("15.00"))
                .stock(50)
                .stockCritico(5)
                .categoria(galletas)
                .build();
        productoRepository.save(producto3);

        Producto producto4 = Producto.builder()
                .codigoProducto("PROD004")
                .nombreProducto("Brownie")
                .descripcionProducto("Brownie casero de chocolate")
                .precioProducto(new BigDecimal("25.00"))
                .stock(20)
                .stockCritico(3)
                .categoria(postres)
                .build();
        productoRepository.save(producto4);

        System.out.println("✓ Categorías y Productos creados exitosamente");
    }

    private void createUsuarios() {
        Usuario admin = Usuario.builder()
                .run("12345678-9")
                .nombre("Admin")
                .apellidos("Pastelería")
                .correo("admin@pasteleria.com")
                .password(passwordEncoder.encode("Admin123!"))
                .fechaNacimiento(LocalDate.of(1990, 1, 1))
                .tipoUsuario(Usuario.TipoUsuario.SUPERADMIN)
                .build();
        usuarioRepository.save(admin);

        Usuario vendedor = Usuario.builder()
                .run("98765432-1")
                .nombre("Vendedor")
                .apellidos("Pastelería")
                .correo("vendedor@pasteleria.com")
                .password(passwordEncoder.encode("Vendedor123!"))
                .fechaNacimiento(LocalDate.of(1995, 5, 15))
                .tipoUsuario(Usuario.TipoUsuario.VENDEDOR)
                .build();
        usuarioRepository.save(vendedor);

        System.out.println("✓ Usuarios creados exitosamente");
    }
}
