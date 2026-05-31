package com.aula.apibiblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_emprestimos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "emprestimo_id"), @JoinColumn(name="emprestimo_id")})
    private List<Livro> livro = new ArrayList<>();

    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;
}
