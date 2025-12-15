package com.pasteleria.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 20)
    @NotBlank(message = "El código del producto es obligatorio")
    private String codigoProducto;
    
    @Column(nullable = false, length = 200)
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombreProducto;
    
    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private BigDecimal precioProducto;
    
    @Column(columnDefinition = "TEXT")
    private String descripcionProducto;
    
    @Column(length = 500)
    private String imagenProducto;
    
    @Column(nullable = false)
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;
    
    @Column(nullable = false)
    @Min(value = 0, message = "El stock crítico no puede ser negativo")
    private Integer stockCritico;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    @JsonIgnore
    private Categoria categoria;
}
