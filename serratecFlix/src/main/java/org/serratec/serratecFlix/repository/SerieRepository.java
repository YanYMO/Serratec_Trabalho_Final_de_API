package org.serratec.serratecFlix.repository;

import org.serratec.serratecFlix.entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepository extends JpaRepository <Serie, Long>{
    Boolean existsByTitulo(String titulo);

    List<Serie> findByCategoriasId(Long id);
}
