package org.serratec.serratecFlix.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
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

    
    @Column(name = "epsodios", nullable = false)
    private Integer episodios;

   
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "data_de_lancamento", nullable = false)
    private LocalDate dataLancamento;

    @Column(name = "nota_media", nullable = true)
    private Double notaMedia;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Premio> premios = new ArrayList<>();
}
