package com.aula.apibiblioteca.dto;

import lombok.Builder;

// Retornado tanto no cadastro quanto no login
@Builder
public record AuthResponseDto(
        Long          id,
        String        nome,
        String        email,
        String        role,
        String        token
) {}
