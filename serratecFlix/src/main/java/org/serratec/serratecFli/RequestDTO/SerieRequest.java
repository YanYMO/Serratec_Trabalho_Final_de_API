package org.serratec.serratecFli.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.serratec.serratecFlix.entity.AvaliacaoSerie;
import org.serratec.serratecFlix.entity.Categoria;

import java.time.LocalDate;
import java.util.List;

@Data
public class SerieRequest {

    @NotBlank(message = "Este campo precisa ser preenchido")
    private String titulo;

    @NotBlank(message = "Este campo precisa ser preenchido")
    private String descricao;

    @NotNull(message = "Este campo precisa ser preenchido")
    private Integer temporadas;

    @NotNull(message = "Este campo precisa ser preenchido")
    private Integer episodios;

    @NotNull(message = "Este campo precisa ser preenchido")
    @Past
    private LocalDate dataLancamento;

    private List<AvaliacaoSerie> avaliacoesSeries;

    @NotBlank
    private List<Categoria> categorias;
}
