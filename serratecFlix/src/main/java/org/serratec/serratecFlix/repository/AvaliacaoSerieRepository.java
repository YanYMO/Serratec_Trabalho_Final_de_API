package org.serratec.serratecFlix.repository;

import org.serratec.serratecFlix.entity.AvaliacaoSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoSerieRepository extends JpaRepository <AvaliacaoSerie, Long> {
    Boolean existsByUsuarioID(Long idUsuario);

}
