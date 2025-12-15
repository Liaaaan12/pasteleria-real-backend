package com.pasteleria.api.repository;

import com.pasteleria.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);
    Optional<Usuario> findByRun(String run);
    boolean existsByCorreo(String correo);
    boolean existsByRun(String run);
}
