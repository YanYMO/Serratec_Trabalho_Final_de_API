package org.serratec.serratecFlix.dto.responsedto;

import lombok.Data;
import org.serratec.serratecFlix.entity.AvaliacaoSerie;
import java.time.LocalDate;

@Data
public class AvaliacaoSerieResponseDTO {

    private Integer nota;
    private String comentario;
    private LocalDate dataAvaliacao;
    private String nomeUsuario;

    public AvaliacaoSerieResponseDTO(AvaliacaoSerie avaliacaoSerie){
        this.nota = avaliacaoSerie.getNota();
        this.comentario = avaliacaoSerie.getComentario();
        this.dataAvaliacao = avaliacaoSerie.getDataAvaliacao();
    }
}
