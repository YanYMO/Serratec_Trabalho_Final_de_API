package org.serratec.serratecFlix.DTO.ResponseDTO;

import lombok.Data;
import org.serratec.serratecFlix.entity.Usuario;

import java.time.LocalDate;

@Data

public class UsuarioResponseDTO {

    private String nome;
    private String userName;
    private LocalDate dataCriacao;
    
    public UsuarioResponseDTO(Usuario usuario){
    	this.nome = usuario.getNome();
    	this.userName = usuario.getUsername();
    	this.dataCriacao = usuario.getDataCriacao();
    }
}
