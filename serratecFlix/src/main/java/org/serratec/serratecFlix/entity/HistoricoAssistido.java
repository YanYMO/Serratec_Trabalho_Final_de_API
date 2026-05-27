package org.serratec.serratecFlix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.serratec.serratecFlix.enums.StatusAssistido;

import java.time.LocalDate;

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
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idFilme")
    private Filme filme;

    @ManyToOne
    @JoinColumn(name = "idSerie")
    private Serie serie;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAssistido statusAssistido;

    @Column(nullable = false)
    private LocalDate data;
}
