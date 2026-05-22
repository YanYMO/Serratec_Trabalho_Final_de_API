package org.serratec.serratecFlix.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Este campo precisa ser preenchido")
    @Size(max = 50)
    @Column(name = "titulo", nullable = false, length = 50)
    private String nome;

    @NotBlank(message = "Este campo precisa ser preenchido")
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @ManyToMany(mappedBy = "categorias")
    @JsonBackReference
    private List<Filme> filmes;

    @ManyToMany(mappedBy = "categorias")
    @JsonBackReference
    private List<Serie> series;
}
