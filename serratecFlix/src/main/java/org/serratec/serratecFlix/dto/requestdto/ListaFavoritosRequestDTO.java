package org.serratec.serratecFlix.dto.requestdto;

import java.util.List;

import org.serratec.serratecFlix.entity.Filme;
import org.serratec.serratecFlix.entity.Serie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class ListaFavoritosRequestDTO {

    @NotBlank(message = "O nome da lista e obrigatorio")
    @Size(max = 60, message = "O nome da lista deve ter no maximo 60 caracteres")
    private String nomeLista;

    @NotNull(message = "O tipo da lista e obrigatorio")
    private Boolean privada;

    @NotNull(message = "O id do usuario e obrigatorio")
    private Long idUsuario;

    private List<Filme> filmes;

    private List<Serie> series;
}