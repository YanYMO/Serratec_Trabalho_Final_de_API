package org.serratec.serratecFlix.dto.responsedto;

import lombok.Data;
import org.serratec.serratecFlix.entity.Filme;
import org.serratec.serratecFlix.enums.ClassificacaoIndicativa;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;

@Data

@JsonPropertyOrder({
    "id",
    "titulo",
    "classificao",
    "notaMedia",
    "descricao",
    "duracaoMinutos",
    "dataLancamento"
})

public class FilmeResponseDTO {
	
	private String titulo;
	private String descricao;
	private Integer duracaoMinutos;
	private LocalDate dataLancamento;
	private ClassificacaoIndicativa classificacao;
	private Double notaMedia;
	
	public FilmeResponseDTO(Filme filme) {
		this.titulo = filme.getTitulo();
		this.descricao = filme.getDescricao();
		this.duracaoMinutos = filme.getDuracaoMinutos();
		this.dataLancamento = filme.getDataLancamento();
		this.classificacao = filme.getClassificacao();
		this.notaMedia = filme.getNotaMedia();
	}
}