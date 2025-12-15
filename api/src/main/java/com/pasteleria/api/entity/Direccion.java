package com.pasteleria.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "direcciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Direccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @NotBlank(message = "La dirección es obligatoria")
    private String address;
    
    @Column(nullable = false, length = 100)
    @NotBlank(message = "La región es obligatoria")
    private String region;
    
    @Column(nullable = false, length = 100)
    @NotBlank(message = "La comuna es obligatoria")
    private String comuna;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnore
    private Usuario usuario;
}
