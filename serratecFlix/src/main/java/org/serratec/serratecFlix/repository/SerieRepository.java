package org.serratec.serratecFlix.repository;

import org.serratec.serratecFlix.entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository <Serie, Long>{
    Boolean existsByTitulo();

}
