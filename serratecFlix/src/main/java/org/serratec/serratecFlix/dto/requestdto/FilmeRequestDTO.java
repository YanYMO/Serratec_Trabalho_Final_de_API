package org.serratec.serratecFlix.dto.requestdto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
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

    @Schema(type = "integer", example = "1", description = "Classificação: 1 = Livre, 2 = 10 anos, 3 = 12 anos, 4 = 14 anos, 5 = 16 anos, 6 = 18 anos")
    @NotNull(message = "A classificacao do filme é obrigatoria")
    private ClassificacaoIndicativa classificacao;
    
    @NotNull(message = "O filme precisa ter pelo menos uma categoria.")
    private List<Long> categorias;
}
