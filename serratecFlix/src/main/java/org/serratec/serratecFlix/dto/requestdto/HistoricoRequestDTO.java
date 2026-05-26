package org.serratec.serratecFlix.dto.requestdto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.serratec.serratecFlix.enums.StatusAssistido;

@Data
public class HistoricoRequestDTO {

    private Long idFilme;
    private Long idSerie;
    private StatusAssistido status;
}
