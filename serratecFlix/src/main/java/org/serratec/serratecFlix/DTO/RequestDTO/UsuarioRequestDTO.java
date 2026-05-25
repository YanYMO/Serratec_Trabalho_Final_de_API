package org.serratec.serratecFlix.DTO.RequestDTO;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data

public class UsuarioRequestDTO {

    @NotBlank(message = "O nome do usuario e obrigatorio")
    @Size(max = 80, message = "O nome deve ter no maximo 80 caracteres")
    private String nome;

    @NotNull(message = "A data de nascimento e obrigatoria")
    @Past(message = "Data inválida! A data deve ser no passado")
    private LocalDate dataNascimento;

    @NotBlank(message = "O email e obrigatorio")
    @Email(message = "Email preenchido de forma incorreta")
    @Size(max = 80, message = "O email deve ter no maximo 80 caracteres")
    private String email;

    @NotBlank(message = "O username e obrigatorio")
    @Size(max = 40, message = "O userName deve ter no maximo 40 caracteres")
    private String username;

    @NotBlank(message = "A senha nao pode ser nula")
    @Size(min = 10, message = "A senha deve ter no minimo 10 caracteres")
    private String senha;
}