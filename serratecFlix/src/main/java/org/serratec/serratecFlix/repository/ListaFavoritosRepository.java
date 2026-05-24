package org.serratec.serratecFlix.repository;

import org.serratec.serratecFlix.entity.ListaFavoritos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaFavoritosRepository extends JpaRepository <ListaFavoritos, Long> {
      
      Boolean existsByNomeLista(String nomeLista);

}
