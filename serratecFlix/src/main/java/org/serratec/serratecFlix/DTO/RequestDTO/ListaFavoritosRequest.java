package org.serratec.serratecFlix.DTO.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.serratec.serratecFlix.entity.Filme;
import org.serratec.serratecFlix.entity.Serie;
import org.serratec.serratecFlix.entity.Usuario;

import java.util.List;

@Data
public class ListaFavoritosRequest {

    @NotBlank(message = "Este campo precisa ser preenchido")
    @Size(max = 60)
    private String nomeLista;

    @NotNull(message = "Este campo precisa ser preenchido")
    private Boolean privada;

    @NotNull(message = "Este campo precisa ser preenchido")
    private Usuario usuario;

    private List<Filme> filmes;

    private List<Serie> series;

}