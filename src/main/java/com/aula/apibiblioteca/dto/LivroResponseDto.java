package com.aula.apibiblioteca.dto;

public record LivroResponseDto(
        Long id,
        String titulo,
        String autor,
        Integer quantidade
) {
}
