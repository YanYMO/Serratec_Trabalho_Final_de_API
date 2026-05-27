package org.serratec.serratecFlix.dto.responsedto;

import java.time.LocalDate;

import org.serratec.serratecFlix.entity.AvaliacaoFilme;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data

@JsonPropertyOrder({
    "id",
    "nomeUsuario",
    "dataAvaliacao",
    "nota",
    "comentario"
})

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
