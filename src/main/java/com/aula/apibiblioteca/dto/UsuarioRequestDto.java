package com.aula.apibiblioteca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDto(@NotBlank(message = "Nome não pode ser vazio!") String nome,
                                @NotBlank(message = "Email não pode ser vazio!") @Email(message = "Email Inválido!") String email) {
}
