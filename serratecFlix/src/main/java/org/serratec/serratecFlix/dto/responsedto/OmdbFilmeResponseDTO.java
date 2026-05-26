package org.serratec.serratecFlix.dto.responsedto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class OmdbFilmeResponseDTO {
 @JsonProperty("Title")
    private String titulo;

    @JsonProperty("Year")
    private String ano;

    @JsonProperty("Genre")
    private String genero;

    @JsonProperty("Director")
    private String diretor;

    @JsonProperty("Actors")
    private String atores;

    @JsonProperty("Plot")
    private String sinopse;

    @JsonProperty("Poster")
    private String poster;

    @JsonProperty("imdbRating")
    private String nota;

    @JsonProperty("Runtime")
    private String duracao;

    @JsonProperty("Type")
    private String tipo;

    public OmdbFilmeResponseDTO(String titulo, String ano, String genero, String diretor, String atores, String sinopse,
            String poster, String nota, String duracao, String tipo) {
        this.titulo = titulo;
        this.ano = ano;
        this.genero = genero;
        this.diretor = diretor;
        this.atores = atores;
        this.sinopse = sinopse;
        this.poster = poster;
        this.nota = nota;
        this.duracao = duracao;
        this.tipo = tipo;
    }
    

    

    
}
