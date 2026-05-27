package org.serratec.serratecFlix.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HistoricoCompletoResponseDTO {

    private String nomeUsuario;
    private String emailUsuario;
    private List<HistoricoResponseDTO> filmes;
    private List<HistoricoResponseDTO> series;
}
