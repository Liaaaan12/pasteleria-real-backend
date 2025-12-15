package com.pasteleria.api.service;

import com.pasteleria.api.dto.AuthRequest;
import com.pasteleria.api.dto.AuthResponse;
import com.pasteleria.api.entity.Usuario;
import com.pasteleria.api.repository.UsuarioRepository;
import com.pasteleria.api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getCorreo());
        String token = jwtUtil.generateToken(userDetails);

        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return new AuthResponse(
                token,
                usuario.getCorreo(),
                usuario.getNombre(),
                usuario.getApellidos(),
                usuario.getTipoUsuario().getDisplayName()
        );
    }
}
