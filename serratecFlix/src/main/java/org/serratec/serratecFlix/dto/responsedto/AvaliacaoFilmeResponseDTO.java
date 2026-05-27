package org.serratec.serratecFlix.dto.responsedto;

import lombok.Data;
import org.serratec.serratecFlix.entity.AvaliacaoFilme;

import java.time.LocalDate;

@Data

public class AvaliacaoFilmeResponseDTO {

    private Integer nota;
    private String comentario;
    private LocalDate dataAvaliacao;
    private String nomeUsuario;
    
    public AvaliacaoFilmeResponseDTO(AvaliacaoFilme avaFilme){
    	this.nota = avaFilme.getNota();
    	this.comentario = avaFilme.getComentario();
    	this.dataAvaliacao = avaFilme.getDataAvaliacao();
        this.nomeUsuario = avaFilme.getUsuario().getNome();
    }
}
