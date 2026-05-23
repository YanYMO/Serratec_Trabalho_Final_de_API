package org.serratec.serratecFli.RequestDTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AvaliacaoSerieRequest {

    @NotBlank(message = "Este campo precisa ser preenchido")
    @Min(0)
    @Max(10)
    private Integer nota;

    private String comentário;

    @NotNull(message = "Este campo precisa ser preenchido")
    private Long usuarioId;

    @NotNull(message = "Este campo precisa ser preenchido")
    private Long serieId;
}
