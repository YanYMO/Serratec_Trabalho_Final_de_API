package org.serratec.serratecFlix.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 40)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @ManyToMany(mappedBy = "categorias")
    @JsonIgnore
    private List<Filme> filmes;

    @ManyToMany(mappedBy = "categorias")
    @JsonIgnore
    private List<Serie> series;
}
