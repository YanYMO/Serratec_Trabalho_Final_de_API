package org.serratec.serratecFlix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "avaliacao_serie")
public class AvaliacaoSerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Este campo precisa ser preenchido")
    @Min(0) @Max(10)
    @Column(name = "nota")
    private Integer nota;

    @Column(name = "comentario", nullable = true)
    private String comentario;

    @UpdateTimestamp
    @Column(name = "data_da_avaliacao", nullable = false, updatable = true)
    private LocalDate dataAvaliacao;
}
