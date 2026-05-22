package org.serratec.serratecFlix.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @NotBlank(message = "Este campo precisa ser preenchido")
    @Size(max = 60)
    @Column(name = "titulo", nullable = false, length = 60)
    private String titulo;

    @NotBlank(message = "Este campo precisa ser preenchido")
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @NotNull(message = "Este campo precisa ser preenchido")
    @Column(name = "temporadas", nullable = false)
    private Integer temporadas;

    @NotNull(message = "Este campo precisa ser preenchido")
    @Column(name = "epsodios", nullable = false)
    private Integer episodios;

    @NotNull(message = "Este campo precisa ser preenchido")
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "data_de_lancamento", nullable = false)
    private LocalDate dataLancamento;

    @Column(name = "nota_media", nullable = true)
    private Double notaMedia;

    @OneToMany(mappedBy = "serie")
    @JsonManagedReference
    private List<AvaliacaoSerie> avaliacoesSeries;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "serie_categoria",
            joinColumns = @JoinColumn(name = "id_serie"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    @JsonManagedReference
    private List<Categoria> categorias;
}
