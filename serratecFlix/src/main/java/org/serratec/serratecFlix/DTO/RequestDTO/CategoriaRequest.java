package org.serratec.serratecFlix.DTO.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaRequest {

    @NotBlank(message = "Este campo precisa ser preenchido")
    @Size(max = 500)
    private String nome;

    @NotBlank(message = "Este campo precisa ser preenchido")
    private String descricao;
}
