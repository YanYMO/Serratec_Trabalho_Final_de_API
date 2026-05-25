package org.serratec.serratecFlix.repository;

import org.serratec.serratecFlix.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
        Boolean existsByEmail(String email);
        Boolean existsByUserName(String userName);
        Usuario findByUserName(String username);
}
