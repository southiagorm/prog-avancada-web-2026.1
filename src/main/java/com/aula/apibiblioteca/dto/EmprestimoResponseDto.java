package com.aula.apibiblioteca.dto;

import java.time.LocalDateTime;

public record EmprestimoResponseDto(Long id,
                                    UsuarioResponseDto usuario,
                                    LivroResponseDto livro,
                                    LocalDateTime dataEmprestimo,
                                    LocalDateTime dataDevolucao) {
}
