package org.serratec.serratecFlix.DTO.ResponseDTO;

import lombok.Data;
import org.serratec.serratecFlix.entity.Filme;
import org.serratec.serratecFlix.enums.ClassificacaoIndicativa;

import java.time.LocalDate;

@Data

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