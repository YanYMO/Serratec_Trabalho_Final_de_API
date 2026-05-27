package org.serratec.serratecFlix.dto.responsedto;

import org.serratec.serratecFlix.entity.Experiencia;

public class ExperienciaResponseDTO {
	
	Integer xp;
	
	Integer nivel;

	public ExperienciaResponseDTO(Integer exp, Integer level) {
		super();
		this.xp = exp;
		this.nivel = level;
	}
	
	public ExperienciaResponseDTO(Experiencia exp) {
		super();
		this.xp = exp.getXp();
		this.nivel = exp.getNivel();
	}
}
