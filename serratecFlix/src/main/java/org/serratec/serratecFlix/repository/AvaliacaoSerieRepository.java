package org.serratec.serratecFlix.repository;

import org.serratec.serratecFlix.entity.AvaliacaoSerie;
import org.serratec.serratecFlix.entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoSerieRepository extends JpaRepository <AvaliacaoSerie, Long> {
    Boolean existsByUsuarioId(Long idUsuario);

    /* BUSCANDO TODAS AS AVALIAÇÕES DE UM FILME ESPECÍFICO*/
    /* CALCULANDO A MEDIA DE NOTAS DOS FILME*/
    List<AvaliacaoSerie> findBySerie(Serie serie);

    @Query(value = "SELECT * FROM serie ORDER BY nota_media DESC LIMIT 5", nativeQuery = true)
    List<Serie> top5Series();
}

