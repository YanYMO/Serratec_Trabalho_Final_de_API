package org.serratec.serratecFlix.dto.responsedto;

public class ExperienciaResponseDTO {
	
	Integer xp;
	
	Integer nivel;

	public ExperienciaResponseDTO(Integer exp, Integer level) {
		super();
		this.xp = exp;
		this.nivel = level;
	}
}
