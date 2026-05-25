package org.serratec.serratecFlix.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class CategoriaRequestDTO {

    @NotBlank(message = "A categoria precisa ter um nome")
    @Size(max = 50, message = "O nome da categoria deve ter no maximo 50 caracteres")
    private String nome;

    @NotBlank(message = "A categoria deve ter uma descricao")
    @Size(max = 200, message = "A descricao deve ter no maximo 200 caracteres")
    private String descricao;
}
