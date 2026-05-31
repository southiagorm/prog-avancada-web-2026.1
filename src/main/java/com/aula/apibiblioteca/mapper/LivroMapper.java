package com.aula.apibiblioteca.mapper;

import com.aula.apibiblioteca.dto.LivroRequestDto;
import com.aula.apibiblioteca.dto.LivroResponseDto;
import com.aula.apibiblioteca.model.Livro;

public class LivroMapper {

    public static Livro toEntity(LivroRequestDto requestDto) {
        var livro = new Livro();
        livro.setTitulo(requestDto.titulo());
        livro.setAutor(requestDto.autor());
        livro.setQuantidade(requestDto.quantidade());
        return livro;
    }

    public static LivroResponseDto toDto(Livro livro) {
        return new LivroResponseDto(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getQuantidade()
        );
    }
}
