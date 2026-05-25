package org.serratec.serratecFlix.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.serratec.serratecFlix.exception.EnumValidationException;

public enum ClassificacaoIndicativa {
    LIVRE(1, "Livre", 0),
    DEZ_ANOS(2, "10 anos", 10),
    DOZE_ANOS(3, "12 anos", 12),
    QUATORZE_ANOS(4, "14 anos", 14),
    DEZESEIS_ANOS(5, "16 anos", 16),
    DEZOITO_ANOS(6, "18 anos", 18);

    private Integer codigo;
    private String descricao;
    private Integer idadeMinima;

    ClassificacaoIndicativa(Integer codigo, String descricao, Integer idadeMinima) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.idadeMinima = idadeMinima;
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

    public Integer getIdadeMinima() {
        return idadeMinima;
    }

    public void setIdadeMinima(Integer idadeMinima) {
        this.idadeMinima = idadeMinima;
    }
}
