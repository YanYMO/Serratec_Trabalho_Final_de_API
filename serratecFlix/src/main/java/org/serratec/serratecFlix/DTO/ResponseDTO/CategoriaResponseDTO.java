package org.serratec.serratecFlix.DTO.ResponseDTO;

import org.serratec.serratecFlix.entity.Categoria;

import lombok.Data;

@Data

public class CategoriaResponseDTO {

    private String nome;
    private String descricao;
    
    public CategoriaResponseDTO(Categoria categoria){
    	this.nome = categoria.getNome();
    	this.descricao = categoria.getDescricao();
    }
}
