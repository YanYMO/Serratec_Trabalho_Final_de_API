package org.serratec.serratecFlix.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "premios")
public class Premio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome; 

    @NotBlank
    private String categoria; 

    @NotNull
    private Integer ano;

    @OneToOne
    @JoinColumn(name = "filme_id")
    @JsonIgnore
    private Filme filme;

    @OneToOne
    @JoinColumn(name = "serie_id")
    @JsonIgnore
    private Serie serie;

    public Premio() {
    }

    public Premio(Long id, @NotBlank String nome, @NotBlank String categoria, Integer ano, Boolean vencedor,
            Filme filme, Serie serie) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.ano = ano;
        this.filme = filme;
        this.serie = serie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }
    

    

    

    
}
