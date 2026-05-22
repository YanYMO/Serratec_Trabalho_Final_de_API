package org.serratec.serratecFlix.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.serratec.serratecFlix.exception.EnumValidationException;

public enum ClassificacaoIndicativa {
    LIVRE(1, "Livre"),
    DEZ_ANOS(2, "10 anos"),
    DOZE_ANOS(3, "12 anos"),
    QUATORZE_ANOS(4, "14 anos"),
    DEZESEIS_ANOS(5, "16 anos"),
    DEZOITO_ANOS(6, "18 anos");

    private Integer codigo;
    private String descricao;

    ClassificacaoIndicativa(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    @JsonCreator
    public static ClassificacaoIndicativa verifica(Integer value) throws EnumValidationException {
        for (ClassificacaoIndicativa n : values()) {
            if (value.equals(n.getCodigo())) {
                return n;
            }
        }
        throw new EnumValidationException(
                "Escolaridade Inválida. Valores válidos: " +
                        "1 - Livre, " +
                        "2 - 10 anos, " +
                        "3 - 12 anos, " +
                        "4 - 14 anos, " +
                        "5 - 16 anos, " +
                        "6 - 18 anos");
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
