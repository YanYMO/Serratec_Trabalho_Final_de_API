package org.serratec.serratecFlix.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.serratec.serratecFlix.entity.AvaliacaoFilme;
import org.serratec.serratecFlix.entity.Categoria;
import org.serratec.serratecFlix.enums.ClassificacaoIndicativa;

import java.time.LocalDate;
import java.util.List;

@Data

public class FilmeRequestDTO {

    @NotBlank(message = "O nome do filme e obrigatorio")
    @Size(max = 70, message = "O nome do filme deve ter no maximo 70 caracteres")
    private String titulo;

    @NotBlank(message = "A descricao do filme e obrigatoria")
    @Size(max = 200, message = "A descricao deve ter no maximo 200 caracteres")
    private String descricao;

    @NotNull(message = "A duracao do filme e obrigatoria")
    private Integer duracaoMinutos;

    @NotNull(message = "A data de lancamento do filme é obrigatoria")
    @Past(message = "Data inválida! A data deve ser no passado")
    private LocalDate dataLancamento;

    @NotNull(message = "A classificacao do filme é obrigatoria")
    private ClassificacaoIndicativa classificacao;

    private List<AvaliacaoFilme> avaliacoesFilmes;
    
    @NotBlank
    private List<Categoria> categorias;
}
