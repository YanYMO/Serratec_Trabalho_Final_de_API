package org.serratec.serratecFlix.dto.responsedto;

import org.serratec.serratecFlix.entity.Categoria;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data

@JsonPropertyOrder({
    "id",
    "nome",
    "descricao"
})

public class CategoriaResponseDTO {

    private String nome;
    private String descricao;
    
    public CategoriaResponseDTO(Categoria categoria){
    	this.nome = categoria.getNome();
    	this.descricao = categoria.getDescricao();
    }
}
