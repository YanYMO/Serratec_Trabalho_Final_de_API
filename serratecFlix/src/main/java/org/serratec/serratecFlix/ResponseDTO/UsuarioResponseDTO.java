package org.serratec.serratecFlix.ResponseDTO;

import java.time.LocalDate;

import org.serratec.serratecFlix.entity.Usuario;

import lombok.Data;

@Data

public class UsuarioResponseDTO {

    private String nome;
    private String userName;
    private LocalDate dataCriacao;
    
    UsuarioResponseDTO(Usuario usuario){
    	this.nome = usuario.getNome();
    	this.userName = usuario.getUserName();
    	this.dataCriacao = usuario.getDataCriacao();
    }
}
