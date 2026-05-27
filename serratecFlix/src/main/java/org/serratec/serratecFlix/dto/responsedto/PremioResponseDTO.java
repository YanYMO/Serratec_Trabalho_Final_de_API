package org.serratec.serratecFlix.dto.responsedto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data

@JsonPropertyOrder({
    "id",
    "nome",
    "categoria",
    "ano",
    "nomeConteudo",
    "tipoConteudo"
})

public class PremioResponseDTO {
   
    private Long id;
    private String nome;
    private String categoria;
    private Integer ano;
    private String nomeConteudo;
    private String tipoConteudo;

    
    
    public PremioResponseDTO() {
    }

    public PremioResponseDTO(Long id, String nome, String categoria, Integer ano, String nomeConteudo,
            String tipoConteudo) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.ano = ano;
        this.nomeConteudo = nomeConteudo;
        this.tipoConteudo = tipoConteudo;
    }

    
    
   
    

   
    

    
}
