package org.serratec.serratecFlix.repository;

import org.serratec.serratecFlix.entity.HistoricoAssistido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoAssistidoRepository extends JpaRepository<HistoricoAssistido, Long> {
}
