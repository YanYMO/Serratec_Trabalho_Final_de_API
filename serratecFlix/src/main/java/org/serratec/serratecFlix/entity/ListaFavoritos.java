package org.serratec.serratecFlix.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lista_de_favoritos")
public class ListaFavoritos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_lista", nullable = false, length = 60)
    private String nomeLista;

    @Column(name = "privada", nullable = false)
    private Boolean privada;

    @CreationTimestamp
    @Column(name = "data_de_criacao", nullable = false, updatable = true)
    private LocalDate dataCriacao;

    @ManyToOne()
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "favoritos_filmes",
            joinColumns = @JoinColumn(name = "id_lista_de_favoritos"),
            inverseJoinColumns = @JoinColumn(name = "id_filme"))
    @JsonManagedReference
    private List<Filme> filmes;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "favoritos_series",
            joinColumns = @JoinColumn(name = "id_lista_de_favoritos"),
            inverseJoinColumns = @JoinColumn(name = "id_serie"))
    @JsonManagedReference
    private List<Serie> series;
}
