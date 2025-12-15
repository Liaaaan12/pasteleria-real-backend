package com.pasteleria.api.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private String tipo = "Bearer";
    private String correo;
    private String nombre;
    private String apellidos;
    private String tipoUsuario;
    
    public AuthResponse(String token, String correo, String nombre, String apellidos, String tipoUsuario) {
        this.token = token;
        this.correo = correo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tipoUsuario = tipoUsuario;
    }
}
