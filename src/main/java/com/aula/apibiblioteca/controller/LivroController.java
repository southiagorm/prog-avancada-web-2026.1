package com.aula.apibiblioteca.controller;

import com.aula.apibiblioteca.dto.LivroRequestDto;
import com.aula.apibiblioteca.dto.LivroResponseDto;
import com.aula.apibiblioteca.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<Page<LivroResponseDto>> findAll(@PageableDefault(size = 10) Pageable pagination) {
        return ResponseEntity.ok(livroService.findAll(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDto> findById(@PathVariable Long id) {
        LivroResponseDto responseDto = livroService.findById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public ResponseEntity<LivroResponseDto> save(@RequestBody @Valid LivroRequestDto requestDto) {
        LivroResponseDto createdBook = livroService.save(requestDto);
        return ResponseEntity.status(201).body(createdBook);
    }
}
