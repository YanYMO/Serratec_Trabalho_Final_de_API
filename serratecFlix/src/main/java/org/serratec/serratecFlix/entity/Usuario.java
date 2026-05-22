package org.serratec.serratecFlix.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@Table(name = "usuario")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Este campo precisa ser preenchido")
    @Size(max = 80)
    @Column(name = "nome", nullable = false, length = 80)
    private String nome;

    @NotNull(message = "Este campo precisa ser preenchido")
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "data_de_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @NotBlank(message = "Este campo precisa ser preenchido")
    @Email(message = "Email preenchido de forma incorreta")
    @Size(max = 80)
    @Column(name = "email", nullable = false, length = 80)
    private String email;

    @NotBlank(message = "Este campo precisa ser preenchido")
    @Size(max = 40)
    @Column(name = "use_name", nullable = false, length = 40, unique = true)
    private String userName;

    @NotBlank(message = "Este campos precisa ser preenchido")
    @Size(min = 10)
    @Column(name = "senha", nullable = false)
    private String senha;

    @CreationTimestamp
    @Column(name = "data_de_criacao", nullable = false)
    private LocalDate dataCriacao;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<AvaliacaoFilme> avaliacaoFilme;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<AvaliacaoSerie> avaliacaoSerie;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<ListaFavoritos> listaFavoritos;
}
