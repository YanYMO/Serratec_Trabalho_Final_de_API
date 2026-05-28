package org.serratec.serratecFlix.repository;

import org.serratec.serratecFlix.entity.HistoricoAssistido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoAssistidoRepository extends JpaRepository<HistoricoAssistido, Long> {
    List<HistoricoAssistido> findByUsuarioId(Long idUsuario);

    List<HistoricoAssistido> findBySerieId(Long id);

    List<HistoricoAssistido> findByFilmeId(Long id);
}
