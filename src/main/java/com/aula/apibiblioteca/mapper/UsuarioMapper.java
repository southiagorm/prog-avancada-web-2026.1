package com.aula.apibiblioteca.mapper;

import com.aula.apibiblioteca.dto.UsuarioRequestDto;
import com.aula.apibiblioteca.dto.UsuarioResponseDto;
import com.aula.apibiblioteca.model.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDto usuarioRequestDto){
        var usuario = new Usuario();
        usuario.setNome(usuarioRequestDto.nome());
        usuario.setEmail(usuarioRequestDto.email());
        return usuario;
    }

    public static UsuarioResponseDto toDto(Usuario usuario){
        return new UsuarioResponseDto(usuario.getId(),
                usuario.getNome(),
                usuario.getEmail());
    }
}
