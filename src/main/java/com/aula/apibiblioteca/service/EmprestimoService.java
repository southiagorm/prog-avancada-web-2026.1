package com.aula.apibiblioteca.service;

import com.aula.apibiblioteca.dto.EmprestimoRequestDto;
import com.aula.apibiblioteca.dto.EmprestimoResponseDto;
import com.aula.apibiblioteca.mapper.EmprestimoMapper;
import com.aula.apibiblioteca.model.Emprestimo;
import com.aula.apibiblioteca.repository.EmprestimoRepository;
import com.aula.apibiblioteca.repository.LivroRepository;
import com.aula.apibiblioteca.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmprestimoService {


    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private EmprestimoMapper mapper;

    public EmprestimoResponseDto emprestar(EmprestimoRequestDto emprestimoRequestDto){

        var usuario = usuarioRepository.findById(emprestimoRequestDto.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario não Encontrato"));

        var livro = livroRepository.findById(emprestimoRequestDto.livroId())
                .orElseThrow(() -> new EntityNotFoundException("Livro não Encontrado"));

        //verificar quantidade de livros
        //salvar quantidade atualizada do livro emprestado

        var emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);

        var dataEmprestimo = LocalDateTime.now();
        var dataDevolucao = dataEmprestimo.plusMonths(1);

        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataDevolucao(dataDevolucao);
        var emprestimoSaved = emprestimoRepository.save(emprestimo);
        System.out.println(emprestimoSaved);
        return mapper.toDto(emprestimoSaved);
    }
}
