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
@Table(name = "serie")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 60)
    private String titulo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "temporadas", nullable = false)
    private Integer temporadas;

    @Column(name = "episodios", nullable = false)
    private Integer episodios;

    @Column(name = "data_de_lancamento", nullable = false)
    private LocalDate dataLancamento;

    @Column(name = "nota_media", nullable = true)
    private Double notaMedia;

    @Column(name = "classificacao_indicativa", nullable = false)
    private ClassificacaoIndicativa classificacao;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AvaliacaoSerie> avaliacoesSeries;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "serie_categoria",
            joinColumns = @JoinColumn(name = "id_serie"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    @JsonManagedReference
    private List<Categoria> categorias;
}
