package org.serratec.serratecFlix.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.serratec.serratecFlix.enums.StatusAssistido;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "historico_assistido")
public class HistoricoAssistido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idFilme")
    @JsonBackReference
    private Filme filme;

    @ManyToOne
    @JoinColumn(name = "idSerie")
    @JsonBackReference
    private Serie serie;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAssistido statusAssistido;

    @Column(nullable = false)
    private LocalDateTime data;
}
