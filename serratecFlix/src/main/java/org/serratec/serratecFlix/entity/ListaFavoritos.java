package org.serratec.serratecFlix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

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

    @NotBlank(message = "Este campo precisa ser preenchido")
    @Size(max = 60)
    @Column(name = "nome_lista", nullable = false, length = 60)
    private String nomeLista;

    @NotNull(message = "Este campo precisa ser preenchido")
    @Column(name = "privada", nullable = false)
    private Boolean privada;

    @CreationTimestamp
    @Column(name = "data_de_criacao", nullable = false, updatable = true)
    private LocalDate dataCriacao;
}
