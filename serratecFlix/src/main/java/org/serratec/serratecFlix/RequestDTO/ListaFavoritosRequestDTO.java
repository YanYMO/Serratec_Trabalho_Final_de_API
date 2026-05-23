package org.serratec.serratecFlix.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.serratec.serratecFlix.entity.Filme;
import org.serratec.serratecFlix.entity.Serie;
import org.serratec.serratecFlix.entity.Usuario;

import java.util.List;

@Data

public class ListaFavoritosRequestDTO {

    @NotBlank(message = "O nome da lista e obrigatorio")
    @Size(max = 60, message = "A senha deve ter no maximo 60 caracteres")
    private String nomeLista;

    @NotNull(message = "O tipo da lista e obrigatorio")
    private Boolean privada;

    @NotNull(message = "O usuario e obrigatorio")
    private Usuario usuario;

    private List<Filme> filmes;

    private List<Serie> series;
}