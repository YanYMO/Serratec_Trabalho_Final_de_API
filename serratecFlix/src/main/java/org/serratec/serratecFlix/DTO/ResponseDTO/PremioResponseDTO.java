package org.serratec.serratecFlix.DTO.ResponseDTO;



public class PremioResponseDTO {
   
    private Long id;
    private String nome;
    private String categoria;
    private Integer ano;
    private String nomeConteudo;
    private String tipoConteudo;


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
   
    
   
    

   
    

    
}
