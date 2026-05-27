package org.serratec.serratecFlix.dto.responsedto;

import lombok.Data;
import org.serratec.serratecFlix.entity.HistoricoAssistido;
import org.serratec.serratecFlix.enums.StatusAssistido;

import java.time.LocalDateTime;

@Data
public class HistoricoResponseDTO {

    private Long id;
    private String titulo;
    private String tipo;
    private StatusAssistido statusAssistido;
    private LocalDateTime data;

    public HistoricoResponseDTO(HistoricoAssistido historicoAssistido) {
        this.id = historicoAssistido.getId();
        if(historicoAssistido.getFilme() == null) {
            this.titulo = historicoAssistido.getSerie().getTitulo();
            this.tipo = "SERIE";
        }
        if(historicoAssistido.getSerie() == null) {
            this.titulo = historicoAssistido.getFilme().getTitulo();
            this.tipo = "FILME";
        }
        this.statusAssistido = historicoAssistido.getStatusAssistido();
        this.data = historicoAssistido.getData();
    }
}
