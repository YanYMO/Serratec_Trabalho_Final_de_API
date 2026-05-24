package org.serratec.serratecFlix.repository;

import org.serratec.serratecFlix.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Long> {
        Boolean existsByNome();

        
}
