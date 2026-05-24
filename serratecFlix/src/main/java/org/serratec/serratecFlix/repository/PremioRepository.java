package org.serratec.serratecFlix.repository;

import java.util.List;

import org.serratec.serratecFlix.entity.Premio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PremioRepository extends JpaRepository<Premio, Long> {
    

    
    List<Premio> findByFilmeId(Long idFilme);


    List<Premio> findBySerieId(Long idSerie);

    @Query(value = "SELECT * FROM premios WHERE ano = :ano ORDER BY nome ASC", nativeQuery = true)
    List<Premio> buscarPorAno(@Param("ano") Integer ano);
    
    
   
}
