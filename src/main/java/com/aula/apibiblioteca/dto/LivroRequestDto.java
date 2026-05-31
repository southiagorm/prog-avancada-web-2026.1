package com.aula.apibiblioteca.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroRequestDto(
        @NotBlank(message = "Título não pode ser vazio!")
        String titulo,

        @NotBlank(message = "Autor não pode ser vazio!")
        String autor,

        @NotNull(message = "Quantidade não pode ser nula!")
        @Min(value = 0, message = "Quantidade deve ser maior ou igual a zero!")
        Integer quantidade
) {
}
