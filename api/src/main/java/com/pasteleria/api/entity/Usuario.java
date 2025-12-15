package com.pasteleria.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 12)
    @NotBlank(message = "El RUN es obligatorio")
    private String run;
    
    @Column(nullable = false, length = 100)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    @Column(nullable = false, length = 100)
    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;
    
    @Column(unique = true, nullable = false, length = 150)
    @Email(message = "El correo debe ser válido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;
    
    @Column(nullable = false)
    private LocalDate fechaNacimiento;
    
    @Column(nullable = false)
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipoUsuario;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Direccion> direcciones = new HashSet<>();
    
    public enum TipoUsuario {
        SUPERADMIN("SuperAdmin"),
        ADMINISTRADOR("Administrador"),
        VENDEDOR("Vendedor"),
        CLIENTE("Cliente");
        
        private final String displayName;
        
        TipoUsuario(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
        
        public static TipoUsuario fromDisplayName(String displayName) {
            for (TipoUsuario tipo : values()) {
                if (tipo.displayName.equalsIgnoreCase(displayName)) {
                    return tipo;
                }
            }
            throw new IllegalArgumentException("Tipo de usuario no válido: " + displayName);
        }
    }
}
