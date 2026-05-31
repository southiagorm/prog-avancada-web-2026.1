package com.aula.apibiblioteca.service;

import com.aula.apibiblioteca.dto.LivroRequestDto;
import com.aula.apibiblioteca.dto.LivroResponseDto;
import com.aula.apibiblioteca.mapper.LivroMapper;
import com.aula.apibiblioteca.model.Livro;
import com.aula.apibiblioteca.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Page<LivroResponseDto> findAll(Pageable pagination) {
        return livroRepository.findAll(pagination).map(LivroMapper::toDto);
    }

    public LivroResponseDto findById(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não Encontrado"));
        return LivroMapper.toDto(livro);
    }

    public LivroResponseDto save(LivroRequestDto requestDto) {
        Livro livro = LivroMapper.toEntity(requestDto);
        return LivroMapper.toDto(livroRepository.save(livro));
    }
}
