package org.serratec.serratecFlix.repository;

import org.serratec.serratecFlix.entity.AvaliacaoFilme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AvaliacaoFilmeRepository extends JpaRepository <AvaliacaoFilme, Long>{
     Boolean existsByUsuarioID(Long idUsuario);

}
