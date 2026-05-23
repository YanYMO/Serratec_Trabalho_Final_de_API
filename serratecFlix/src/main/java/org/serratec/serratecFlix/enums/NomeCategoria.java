package org.serratec.serratecFlix.enums;


import org.serratec.serratecFlix.exception.EnumValidationException;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum NomeCategoria {

    ACAO(1, "Ação"),
    COMEDIA(2, "Comédia"),
    ROMANCE(3, "Romance"),
    TERROR(4, "Terror"),
    BIOGRAFIA(5, "Biografia"),
    MUSICAL(6, "Musical");

    private Integer codigo;
    private String descricao;

    NomeCategoria(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    @JsonCreator
    public static NomeCategoria verifica(Integer value) throws EnumValidationException {
        for (NomeCategoria c : values()) {
            if (value.equals(c.getCodigo())) {
                return c;
            }
        }
        throw new EnumValidationException(
                "Categoria Inválida. Valores válidos: " +
                        "1 - Ação, " +
                        "2 - Comédia, " +
                        "3 - Romance, " +
                        "4 - Terror, " +
                        "5 - Biografia, " +
                        "6 - Musical");
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

