package org.serratec.serratecFlix.dto.requestdto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.serratec.serratecFlix.entity.Categoria;
import org.serratec.serratecFlix.enums.ClassificacaoIndicativa;

import java.time.LocalDate;
import java.util.List;

@Data

public class SerieRequestDTO {

	@NotBlank(message = "O nome do serie e obrigatorio")
	@Size(max = 50, message = "O nome deve ter no maximo 50 caracteres")
    private String titulo;

	@NotBlank(message = "A descricao da serie e obrigatoria")
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

    @NotNull(message = "A classificacao da serie é obrigatoria")
    private ClassificacaoIndicativa classificacao;

    @NotNull(message = "A serie precisa ter pelo menos uma categoria.")
    private List<Categoria> categorias;
}
