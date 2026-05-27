package org.serratec.serratecFlix.dto.responsedto;

import org.serratec.serratecFlix.entity.Experiencia;
import org.serratec.serratecFlix.entity.Usuario;

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
    "userName",
    "nivel",
    "xp"
})

public class ExperienciaResponseDTO {
	
	String userName;
	Integer xp;
	Integer nivel;
	
	public ExperienciaResponseDTO(Experiencia exp) {
		super();
		this.userName = exp.getUsuario().getUsername();
		this.xp = exp.getXp();
		this.nivel = exp.getNivel();
	}
	
	public ExperienciaResponseDTO(Usuario user) {
		super();
		this.userName = user.getNome();
		this.xp = user.getExperiencia().getXp();
		this.nivel = user.getExperiencia().getNivel();
	}
}
