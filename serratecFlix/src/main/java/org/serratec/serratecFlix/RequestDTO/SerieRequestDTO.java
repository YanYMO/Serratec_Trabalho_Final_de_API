package org.serratec.serratecFlix.RequestDTO;

import java.time.LocalDate;
import java.util.List;

import org.serratec.serratecFlix.entity.AvaliacaoSerie;
import org.serratec.serratecFlix.entity.Categoria;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class SerieRequestDTO {

	@NotBlank(message = "O nome do serie e obrigatorio")
	@Size(max = 50, message = "O nome deve ter no maximo 50 caracteres")
    private String titulo;

	@NotBlank(message = "A descricao do filme e obrigatoria")
	@Size(max = 200, message = "A descricao deve ter no maximo 200 caracteres")
    private String descricao;

    @NotNull(message = "A quantidade de temporadas e obrigatoria")
    @Min(value = 1, message = "A serie deve ter pelo menos 1 temporada")
    private Integer temporadas;

    @NotNull(message = "A quantidade de episodios e obrigatoria")
    @Min(value = 1, message = "A serie deve ter pelo menos 1 episodio")
    private Integer episodios;

    @NotNull(message = "A data de lancamento e obrigatoria")
    @Past(message = "Data inválida! A data deve ser no passado")
    private LocalDate dataLancamento;

    private List<AvaliacaoSerie> avaliacoesSeries;

    @NotBlank
    private List<Categoria> categorias;
}
