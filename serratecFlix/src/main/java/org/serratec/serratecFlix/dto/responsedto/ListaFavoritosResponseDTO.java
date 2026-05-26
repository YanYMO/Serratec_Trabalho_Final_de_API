package org.serratec.serratecFlix.dto.responsedto;

import lombok.Data;
import org.serratec.serratecFlix.entity.Filme;
import org.serratec.serratecFlix.entity.ListaFavoritos;
import org.serratec.serratecFlix.entity.Serie;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Data
public class ListaFavoritosResponseDTO {

    private String nomeLista;
    private LocalDate dataCriacao;
    private List<String> filmes;
    private List<String> series;

    public ListaFavoritosResponseDTO(ListaFavoritos listaFavoritos) {
        this.nomeLista = listaFavoritos.getNomeLista();
        this.dataCriacao = listaFavoritos.getDataCriacao();
        this.filmes = listaFavoritos.getFilmes() == null? Collections.emptyList() : listaFavoritos.getFilmes().stream().map(Filme :: getTitulo).toList();
        this.series = listaFavoritos.getSeries() == null? Collections.emptyList() : listaFavoritos.getSeries().stream().map(Serie:: getTitulo).toList();
    }
}
