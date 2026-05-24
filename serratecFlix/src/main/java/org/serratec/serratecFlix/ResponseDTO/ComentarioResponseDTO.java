package org.serratec.serratecFlix.ResponseDTO;

import java.time.LocalDateTime;

public class ComentarioResponseDTO {
   
    private Long id;
    private String conteudo;
    private String nomeUsuario;
    private String nomeConteudo;
    private String tipoConteudo;
    private LocalDateTime dataCriacao;
   
    
   
    public ComentarioResponseDTO() {
    }


    public ComentarioResponseDTO(Long id, String conteudo, String nomeUsuario, String nomeConteudo, String tipoConteudo,
            LocalDateTime dataCriacao) {
        this.id = id;
        this.conteudo = conteudo;
        this.nomeUsuario = nomeUsuario;
        this.nomeConteudo = nomeConteudo;
        this.tipoConteudo = tipoConteudo;
        this.dataCriacao = dataCriacao;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getConteudo() {
        return conteudo;
    }


    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }


    public String getNomeUsuario() {
        return nomeUsuario;
    }


    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }


    public String getNomeConteudo() {
        return nomeConteudo;
    }


    public void setNomeConteudo(String nomeConteudo) {
        this.nomeConteudo = nomeConteudo;
    }


    public String getTipoConteudo() {
        return tipoConteudo;
    }


    public void setTipoConteudo(String tipoConteudo) {
        this.tipoConteudo = tipoConteudo;
    }


    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }


    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

   
    

    
}
