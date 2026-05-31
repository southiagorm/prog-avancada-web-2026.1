package com.aula.apibiblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_livros")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String titulo;

    @Column(length = 255)
    private String autor;

    private Integer quantidade;
}
