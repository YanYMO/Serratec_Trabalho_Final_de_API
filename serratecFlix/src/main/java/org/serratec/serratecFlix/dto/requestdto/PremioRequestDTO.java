package org.serratec.serratecFlix.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PremioRequestDTO {
  
   @NotBlank
    private String nome;

    @NotBlank
    private String categoria;

    @NotNull
    private Integer ano;

    private Long filmeId;
    private Long serieId;

    
    public PremioRequestDTO() {
    }

    public PremioRequestDTO(@NotBlank String nome, @NotBlank String categoria, @NotNull Integer ano, Long filmeId,
            Long serieId) {
        this.nome = nome;
        this.categoria = categoria;
        this.ano = ano;
        this.filmeId = filmeId;
        this.serieId = serieId;
    }

    
    

    
}
