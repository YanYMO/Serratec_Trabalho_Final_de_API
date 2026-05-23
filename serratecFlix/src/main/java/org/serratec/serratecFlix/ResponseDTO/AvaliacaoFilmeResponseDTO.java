package org.serratec.serratecFlix.ResponseDTO;

import java.time.LocalDate;

import org.serratec.serratecFlix.entity.AvaliacaoFilme;

import lombok.Data;

@Data

public class AvaliacaoFilmeResponseDTO {
	

    private Integer nota;
    private String comentario;
    private LocalDate dataAvaliacao;
    private String nomeUsuario;
    
    AvaliacaoFilmeResponseDTO(AvaliacaoFilme avaFilme){
    	this.nota = avaFilme.getNota();
    	this.comentario = avaFilme.getComentario();
    	this.dataAvaliacao = avaFilme.getDataAvaliacao();
    }
}
