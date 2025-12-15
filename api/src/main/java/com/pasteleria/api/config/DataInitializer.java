package com.pasteleria.api.config;

import com.pasteleria.api.entity.Categoria;
import com.pasteleria.api.entity.Direccion;
import com.pasteleria.api.entity.Producto;
import com.pasteleria.api.entity.Usuario;
import com.pasteleria.api.repository.CategoriaRepository;
import com.pasteleria.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) {
        // Crear usuarios de prueba
        createUsuarios();
        // Crear categorías y productos
        createCategoriasYProductos();
    }

    private void createUsuarios() {
        // SuperAdmin
        Usuario superAdmin = Usuario.builder()
                .run("11111111-1")
                .nombre("Ana María")
                .apellidos("Pérez Soto")
                .correo("ana.maria@gmail.cl")
                .fechaNacimiento(LocalDate.of(1990, 5, 12))
                .tipoUsuario(Usuario.TipoUsuario.SUPERADMIN)
                .password("61be55a8e2f6b4e172338bddf184d6dbee29c98853e0a0485ecee7f27b9af0b4")
                .build();
        
        Direccion dir1 = Direccion.builder()
                .address("Av. Libertador 123")
                .region("Metropolitana")
                .comuna("Santiago")
                .usuario(superAdmin)
                .build();
        
        superAdmin.setDirecciones(Set.of(dir1));
        usuarioRepository.save(superAdmin);

        // Administrador
        Usuario admin = Usuario.builder()
                .run("12345678-9")
                .nombre("Luis Felipe")
                .apellidos("González Fuentes")
                .correo("luis.felipe@gmail.com")
                .fechaNacimiento(LocalDate.of(1985, 11, 20))
                .tipoUsuario(Usuario.TipoUsuario.ADMINISTRADOR)
                .password("61be55a8e2f6b4e172338bddf184d6dbee29c98853e0a0485ecee7f27b9af0b4")
                .build();
        
        Direccion dir2 = Direccion.builder()
                .address("Calle 5 Norte 456")
                .region("Valparaíso")
                .comuna("Viña del Mar")
                .usuario(admin)
                .build();
        
        admin.setDirecciones(Set.of(dir2));
        usuarioRepository.save(admin);

        // Cliente
        Usuario cliente = Usuario.builder()
                .run("16789032-2")
                .nombre("Claudia Isabel")
                .apellidos("Fernández Mella")
                .correo("claudia.isabel@duoc.cl")
                .fechaNacimiento(LocalDate.of(1992, 10, 26))
                .tipoUsuario(Usuario.TipoUsuario.CLIENTE)
                .password("61be55a8e2f6b4e172338bddf184d6dbee29c98853e0a0485ecee7f27b9af0b4")
                .build();
        
        Direccion dir3 = Direccion.builder()
                .address("Av. San Miguel 876")
                .region("Maule")
                .comuna("Talca")
                .usuario(cliente)
                .build();
        
        cliente.setDirecciones(Set.of(dir3));
        usuarioRepository.save(cliente);
    }

    private void createCategoriasYProductos() {
        // Categoría: Tortas Cuadradas
        Categoria tortasCuadradas = Categoria.builder()
                .nombreCategoria("Tortas Cuadradas")
                .build();
        
        Producto tc001 = Producto.builder()
                .codigoProducto("TC001")
                .nombreProducto("Torta Cuadrada de Chocolate")
                .precioProducto(new BigDecimal("45000"))
                .descripcionProducto("Deliciosa torta de chocolate con varias capas de esponjoso bizcocho")
                .imagenProducto("/images/products/tortas/tradicional/torta-cuadrada-chocolate.png")
                .stock(10)
                .stockCritico(3)
                .categoria(tortasCuadradas)
                .build();
        
        Producto tc002 = Producto.builder()
                .codigoProducto("TC002")
                .nombreProducto("Torta Cuadrada de Frutas")
                .precioProducto(new BigDecimal("50000"))
                .descripcionProducto("Una mezcla exquisita de frutas frescas de temporada")
                .imagenProducto("/images/products/tortas/tradicional/torta-cuadrada-frutas.jpg")
                .stock(10)
                .stockCritico(3)
                .categoria(tortasCuadradas)
                .build();
        
        tortasCuadradas.setProductos(Set.of(tc001, tc002));
        categoriaRepository.save(tortasCuadradas);

        // Categoría: Postres Individuales
        Categoria postresIndividuales = Categoria.builder()
                .nombreCategoria("Postres Individuales")
                .build();
        
        Producto pi001 = Producto.builder()
                .codigoProducto("PI001")
                .nombreProducto("Mousse de Chocolate")
                .precioProducto(new BigDecimal("5000"))
                .descripcionProducto("Postre cremoso y suave, elaborado con chocolate de alta calidad")
                .imagenProducto("/images/products/postres-individuales/mousse-chocolate.png")
                .stock(10)
                .stockCritico(3)
                .categoria(postresIndividuales)
                .build();
        
        postresIndividuales.setProductos(Set.of(pi001));
        categoriaRepository.save(postresIndividuales);
    }
}
