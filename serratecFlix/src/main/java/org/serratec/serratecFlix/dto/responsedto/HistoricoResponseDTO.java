package org.serratec.serratecFlix.dto.responsedto;

import lombok.Data;
import org.serratec.serratecFlix.enums.StatusAssistido;

import java.time.LocalDateTime;

@Data
public class HistoricoResponseDTO {

    private Long id;
    private String titulo;
    private String tipo;
    private StatusAssistido status;
    private LocalDateTime data;
}
