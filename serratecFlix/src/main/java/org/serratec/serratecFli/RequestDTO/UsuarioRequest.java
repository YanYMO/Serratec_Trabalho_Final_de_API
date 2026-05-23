package org.serratec.serratecFli.RequestDTO;

import jakarta.validation.constraints.*;

import java.time.LocalDate;


public class UsuarioRequest {

    @NotBlank(message = "Este campo precisa ser preenchido")
    @Size(max = 80)
    private String nome;

    @NotNull(message = "Este campo precisa ser preenchido")
    @Past
    private LocalDate dataNascimento;

    @NotBlank(message = "Este campo precisa ser preenchido")
    @Email(message = "Email preenchido de forma incorreta")
    @Size(max = 80)
    private String email;

    @NotBlank(message = "Este campo precisa ser preenchido")
    @Size(max = 40)
    private String userName;

    @NotBlank(message = "Este campos precisa ser preenchido")
    @Size(min = 10)
    private String senha;
}