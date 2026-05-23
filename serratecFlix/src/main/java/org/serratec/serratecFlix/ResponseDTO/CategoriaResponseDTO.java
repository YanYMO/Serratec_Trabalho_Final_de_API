package org.serratec.serratecFlix.ResponseDTO;

import org.serratec.serratecFlix.entity.Categoria;

import lombok.Data;

@Data

public class CategoriaResponseDTO {

    private String nome;
    private String descricao;
    
    CategoriaResponseDTO(Categoria categoria){
    	this.nome = categoria.getNome();
    	this.descricao = categoria.getDescricao();
    }
}
