package org.serratec.serratecFlix.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.serratec.serratecFlix.enums.ClassificacaoIndicativa;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "filme")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 60)
    private String titulo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "duracao_em_minutos", nullable = false)
    private Integer duracaoMinutos;

    @Column(name = "data_de_lancamento", nullable = false)
    private LocalDate dataLancamento;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "classificacao_indicativa", nullable = false)
    private ClassificacaoIndicativa classificacao;

    @Column(name = "nota_media", nullable = true)
    private Double notaMedia;

    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AvaliacaoFilme> avaliacoesFilmes;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "filme_categoria",
            joinColumns = @JoinColumn(name = "id_filme"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    @JsonManagedReference
    private List<Categoria> categorias;

    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL)
    private List<Premio> premios;
    @JsonManagedReference
    private List<HistoricoAssistido> historicos;
}
