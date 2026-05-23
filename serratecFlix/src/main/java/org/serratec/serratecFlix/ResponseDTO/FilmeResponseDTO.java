package org.serratec.serratecFlix.ResponseDTO;

import java.time.LocalDate;

import org.serratec.serratecFlix.entity.Filme;
import org.serratec.serratecFlix.enums.ClassificacaoIndicativa;

import lombok.Data;

@Data

public class FilmeResponseDTO {
	
	private String titulo;
	private String descricao;
	private Integer duracaoMinutos;
	private LocalDate dataLançamento;
	private ClassificacaoIndicativa classificacao;
	private Double notaMedia;
	
	public FilmeResponseDTO(Filme filme) {
		this.titulo = filme.getTitulo();
		this.descricao = filme.getDescricao();
		this.duracaoMinutos = filme.getDuracaoMinutos();
		this.dataLançamento = filme.getDataLançamento();
		this.classificacao = filme.getClassificacao();
		this.notaMedia = filme.getNotaMedia();
	}
}