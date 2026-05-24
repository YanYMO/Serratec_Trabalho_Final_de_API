package org.serratec.serratecFlix.DTO.RequestDTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class AvaliacaoSerieRequestDTO {

    @NotBlank(message = "Este campo precisa ser preenchido")
    @Min(value = 0, message = "A nota nao pode ser menor do que 0")
    @Max(value = 10, message = "A nota nao pode ser maior do que 10")
    private Integer nota;
    
    private String comentario;

    @NotNull(message = "O id do Usuario e obrigatorio")
    private Long usuarioId;

    @NotNull(message = "O id da Serie e obrigatorio")
    private Long serieId;
}
