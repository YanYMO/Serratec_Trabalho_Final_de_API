package org.serratec.serratecFlix.dto.responsedto;

import lombok.Data;
import org.serratec.serratecFlix.entity.Usuario;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;

@Data

@JsonPropertyOrder({
    "id",
    "nome",
    "userName",
    "dataCriacao"
})

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
