package org.serratec.serratecFlix.dto.responsedto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.serratec.serratecFlix.entity.HistoricoAssistido;
import org.serratec.serratecFlix.enums.StatusAssistido;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

@Data

@JsonPropertyOrder({
    "id",
    "titulo",
    "tipo",
    "statusAssistido",
    "data"
})

public class HistoricoResponseDTO {

    private Long id;
    private String titulo;
    private String tipo;
    private StatusAssistido statusAssistido;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
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
