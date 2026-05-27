package org.serratec.serratecFlix.dto.requestdto;

import org.serratec.serratecFlix.enums.StatusAssistido;

import lombok.Data;

@Data
public class HistoricoRequestDTO {

    private Long idFilme;
    private Long idSerie;
    private StatusAssistido status;
}
