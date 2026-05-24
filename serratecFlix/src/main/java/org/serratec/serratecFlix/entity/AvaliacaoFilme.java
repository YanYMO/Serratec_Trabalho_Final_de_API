package org.serratec.serratecFlix.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
@Table(name = "avaliacao_filme")
public class AvaliacaoFilme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nota", nullable = false)
    private Integer nota;

    @Column(name = "comentario", nullable = true)
    private String comentario;

    @UpdateTimestamp
    @Column(name = "data_da_avaliacao", nullable = false, updatable = true)
    private LocalDate dataAvaliacao;

    @ManyToOne()
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @ManyToOne()
    @JoinColumn(name = "filme_id", nullable = false)
    @JsonBackReference
    private Filme filme;
}
