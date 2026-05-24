package org.serratec.serratecFlix.ResponseDTO;

import lombok.Data;
import org.serratec.serratecFlix.entity.Serie;

import java.time.LocalDate;

@Data

public class SerieResponseDTO {
	
    private String titulo;
    private String descricao;   
    private Integer temporadas;    
    private Integer episodios;  
    private LocalDate dataLancamento;    
    private Double notaMedia;
    
    public SerieResponseDTO(Serie serie){
    	this.titulo = serie.getTitulo();
    	this.descricao = serie.getDescricao();
    	this.temporadas = serie.getTemporadas();
    	this.episodios = serie.getEpisodios();
    	this.dataLancamento = serie.getDataLancamento();
    	this.notaMedia = serie.getNotaMedia();
    }
}
