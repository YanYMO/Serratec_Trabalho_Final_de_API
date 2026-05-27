package org.serratec.serratecFlix.dto.responsedto;

import org.serratec.serratecFlix.entity.Experiencia;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@JsonPropertyOrder({
    "id",
    "usuarioUsername",
    "nivel",
    "xp"
})

public class ExperienciaResponseDTO {
	
	Integer xp;
	Integer nivel;
	
	public ExperienciaResponseDTO(Experiencia exp) {
		super();
		this.xp = exp.getXp();
		this.nivel = exp.getNivel();
	}
}
