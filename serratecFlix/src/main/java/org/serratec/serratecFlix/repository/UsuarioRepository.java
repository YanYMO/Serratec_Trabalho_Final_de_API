package org.serratec.serratecFlix.repository;

import org.serratec.serratecFlix.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
        Boolean existsByEmail(String email);
        Boolean existsByUsername(String username);
        Usuario findByUsername(String username);
}
