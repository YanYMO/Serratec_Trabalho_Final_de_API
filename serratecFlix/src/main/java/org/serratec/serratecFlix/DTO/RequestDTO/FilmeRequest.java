package org.serratec.serratecFlix.DTO.RequestDTO;

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
public class FilmeRequest {

    @NotBlank(message = "Este campo precisa ser preenchido")
    @Size(max = 50)
    private String titulo;

    @NotBlank(message = "Este campo precisa ser preenchido")
    private String descricao;

    @NotNull(message = "Este campo precisa ser preenchido")
    private Integer duracaoMinutos;

    @NotNull(message = "Este campo precisa ser preenchido")
    @Past
    private LocalDate dataLacamento;

    @NotNull(message = "Este campo precisa ser preenchido")
    private ClassificacaoIndicativa classificacao;

    private List<AvaliacaoFilme> avaliacoesFilmes;

    private List<Categoria> categorias;
}
