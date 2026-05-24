package org.serratec.serratecFlix.repository;

import java.util.List;

import org.serratec.serratecFlix.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
List<Comentario> findByFilmeIdOrderByDataCriacaoDesc(Long filmeId);

    
    List<Comentario> findBySerieIdOrderByDataCriacaoDesc(Long serieId);

    
    @Query("SELECT c FROM Comentario c WHERE c.usuario.id = :usuarioId ORDER BY c.dataCriacao DESC")
    List<Comentario> buscarPorUsuario(@Param("usuarioId") Long usuarioId);

   
    @Query(value = "SELECT * FROM comentarios ORDER BY data_criacao DESC LIMIT :limite", nativeQuery = true)
    List<Comentario> ultimosComentarios(@Param("limite") int limite);
}
