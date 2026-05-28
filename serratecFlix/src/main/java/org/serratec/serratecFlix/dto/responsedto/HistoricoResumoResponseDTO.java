package org.serratec.serratecFlix.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class HistoricoResumoResponseDTO {

    private Long totalFilmes;
    private Long totalSeries;
    private Long assistindo;
    private Long pausado;
    private Long assistido;
}
