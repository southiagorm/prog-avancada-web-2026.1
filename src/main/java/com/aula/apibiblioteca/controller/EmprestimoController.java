package com.aula.apibiblioteca.controller;

import com.aula.apibiblioteca.dto.EmprestimoRequestDto;
import com.aula.apibiblioteca.dto.EmprestimoResponseDto;
import com.aula.apibiblioteca.service.EmprestimoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<EmprestimoResponseDto> emprestimo(@RequestBody @Valid EmprestimoRequestDto emprestimoRequestDto){
        return ResponseEntity.ok(emprestimoService.emprestar(emprestimoRequestDto));
    }

}
