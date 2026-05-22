package org.serratec.serratecFlix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
