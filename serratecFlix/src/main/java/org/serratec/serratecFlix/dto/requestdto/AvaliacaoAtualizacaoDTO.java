package org.serratec.serratecFlix.dto.requestdto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AvaliacaoAtualizacaoDTO {

    @NotNull(message = "A avaliacao precisa ter uma nota")
    @Min(value = 0, message = "A nota minima e 0")
    @Max(value = 10, message = "A nota maxima e 10")
    private Integer nota;

    private String comentario;
}
