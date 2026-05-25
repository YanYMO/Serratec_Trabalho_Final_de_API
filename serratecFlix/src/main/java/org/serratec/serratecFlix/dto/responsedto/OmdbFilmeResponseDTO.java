package org.serratec.serratecFlix.dto.responsedto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
}
