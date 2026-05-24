package org.serratec.serratecFlix.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;




@Entity
@Table(name = "premios")
public class Premio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome; 

    @Column(name = "categoria")
    private String categoria; 

    @Column(name = "ano")
    private Integer ano;

   

    @ManyToOne
    @JoinColumn(name = "filme_id")
    @JsonIgnore
    private Filme filme;

    @ManyToOne
    @JoinColumn(name = "serie_id")
    @JsonIgnore
    private Serie serie;

    

    public Premio() {
    }

    public Premio(Long id, String nome,  String categoria, Integer ano, Boolean vencedor,
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
