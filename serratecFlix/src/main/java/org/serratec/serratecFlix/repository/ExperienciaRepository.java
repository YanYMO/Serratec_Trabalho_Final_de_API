package org.serratec.serratecFlix.repository;

import org.serratec.serratecFlix.entity.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienciaRepository extends JpaRepository <Experiencia, Long> {
        
}