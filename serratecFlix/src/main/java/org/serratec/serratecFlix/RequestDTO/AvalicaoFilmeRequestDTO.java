package org.serratec.serratecFlix.RequestDTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class AvalicaoFilmeRequestDTO {

    @NotNull(message = "A avaliacao precisa ter uma nota")
    @Min(value = 0, message = "A nota minima e 0")
    @Max(value = 10, message = "A nota maxima e 10")
    private Integer nota;

    private String comentario;

    @NotNull(message = "O id do usuario é obrigatorio")
    private Long usuarioId;

    @NotNull(message = "O id do filme é obrigatorio")
    private Long filmeId;
}
