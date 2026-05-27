package org.serratec.serratecFlix.dto.responsedto;

import lombok.Data;
import org.serratec.serratecFlix.entity.AvaliacaoSerie;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;

@Data

@JsonPropertyOrder({
    "id",
    "nomeUsuario",
    "dataAvaliacao",
    "nota",
    "comentario"
})

public class AvaliacaoSerieResponseDTO {

    private Integer nota;
    private String comentario;
    private LocalDate dataAvaliacao;
    private String nomeUsuario;

    public AvaliacaoSerieResponseDTO(AvaliacaoSerie avaliacaoSerie){
        this.nota = avaliacaoSerie.getNota();
        this.comentario = avaliacaoSerie.getComentario();
        this.dataAvaliacao = avaliacaoSerie.getDataAvaliacao();
        this.nomeUsuario = avaliacaoSerie.getUsuario().getNome();
    }
}
