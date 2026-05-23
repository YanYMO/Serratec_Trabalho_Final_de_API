package org.serratec.serratecFlix.repository;



import java.util.List;

import org.serratec.serratecFlix.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository <Filme, Long> {
      Boolean existsByTitulo();

      @Query("SELECT f FROM Filme f ORDER BY f.notaMedia DESC")
      List<Filme> findRankingFilmes();

      



}
