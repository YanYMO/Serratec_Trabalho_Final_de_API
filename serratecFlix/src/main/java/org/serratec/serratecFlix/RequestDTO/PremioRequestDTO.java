package org.serratec.serratecFlix.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class PremioRequestDTO {
  
   @NotBlank
    private String nome;

    @NotBlank
    private String categoria;

    @NotNull
    private Integer ano;

    private Long filmeId;
    private Long serieId;


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

    public Long getFilmeId() {
        return filmeId;
    }
    public void setFilmeId(Long filmeId) {
        this.filmeId = filmeId;
    }
    public Long getSerieId() {
        return serieId;
    }
    public void setSerieId(Long serieId) {
        this.serieId = serieId;
    }

    

    
}
