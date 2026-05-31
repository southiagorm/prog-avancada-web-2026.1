package com.aula.apibiblioteca.mapper;

import com.aula.apibiblioteca.dto.EmprestimoRequestDto;
import com.aula.apibiblioteca.dto.EmprestimoResponseDto;
import com.aula.apibiblioteca.model.Emprestimo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        LivroMapper.class,
        UsuarioMapper.class
})
public interface EmprestimoMapper {

    Emprestimo toEntity(EmprestimoRequestDto emprestimoRequestDto);
    EmprestimoResponseDto toDto(Emprestimo emprestimo);
}
