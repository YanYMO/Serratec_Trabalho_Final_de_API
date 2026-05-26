package org.serratec.serratecFlix.repository;

import org.serratec.serratecFlix.entity.AvaliacaoFilme;
import org.serratec.serratecFlix.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoFilmeRepository extends JpaRepository <AvaliacaoFilme, Long>{
     Boolean existsByUsuarioId(Long idUsuario);

     /* BUSCANDO TODAS AS AVALIAÇÕES DE UM FILME ESPECÍFICO*/
     /* CALCULANDO A MEDIA DE NOTAS DOS FILME*/
     List<AvaliacaoFilme> findByFilme(Filme filme);
}
