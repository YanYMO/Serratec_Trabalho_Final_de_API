package org.serratec.serratecFlix.dto.responsedto;

import lombok.Data;
import org.serratec.serratecFlix.entity.Serie;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.serratec.serratecFlix.enums.ClassificacaoIndicativa;

import java.time.LocalDate;

@Data

@JsonPropertyOrder({
    "id",
    "titulo",
    "classificacao",
    "notaMedia",
    "descricao",
    "temporadas",
    "episodios",
    "dataLancamento"
})

public class SerieResponseDTO {
	
    private String titulo;
    private String descricao;   
    private Integer temporadas;    
    private Integer episodios;  
    private LocalDate dataLancamento;
    private ClassificacaoIndicativa classificacao;
    private Double notaMedia;
    
    public SerieResponseDTO(Serie serie){
    	this.titulo = serie.getTitulo();
    	this.descricao = serie.getDescricao();
    	this.temporadas = serie.getTemporadas();
    	this.episodios = serie.getEpisodios();
    	this.dataLancamento = serie.getDataLancamento();
        this.classificacao = serie.getClassificacao();
    	this.notaMedia = serie.getNotaMedia();
    }
}
