package org.serratec.serratecFlix.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails, Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 80)
    private String nome;

    @Column(name = "data_de_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "email", nullable = false, length = 80, unique = true)
    private String email;

    @Column(name = "username", nullable = false, length = 40, unique = true)
    private String username;

    @Column(name = "senha", nullable = false)
    private String senha;

    @CreationTimestamp
    @Column(name = "data_de_criacao", nullable = false)
    private LocalDate dataCriacao;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AvaliacaoFilme> avaliacaoFilme;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AvaliacaoSerie> avaliacaoSerie;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ListaFavoritos> listaFavoritos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
