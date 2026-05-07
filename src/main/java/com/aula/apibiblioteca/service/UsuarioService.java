package com.aula.apibiblioteca.service;

import com.aula.apibiblioteca.dto.UsuarioEmailRequestDto;
import com.aula.apibiblioteca.dto.UsuarioRequestDto;
import com.aula.apibiblioteca.dto.UsuarioResponseDto;
import com.aula.apibiblioteca.mapper.UsuarioMapper;
import com.aula.apibiblioteca.model.Usuario;
import com.aula.apibiblioteca.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Select sem Where
    public List<UsuarioResponseDto> findAll(){
        return usuarioRepository.findAll().stream().map((u) -> UsuarioMapper.toDto(u)).toList();
    }

    //SELECT * FROM WHERE id=?
    public UsuarioResponseDto findById(Long id){
        var usuario = usuarioRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        return UsuarioMapper.toDto(usuario);
    }

    //Insert
    public UsuarioResponseDto save(UsuarioRequestDto usuarioRequestDto){
        var usuario = UsuarioMapper.toEntity(usuarioRequestDto);
        return UsuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    //Update *
    public UsuarioResponseDto update(Long id, UsuarioRequestDto usuarioRequestDto){
        Usuario usuarioTemp = usuarioRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        usuarioTemp.setNome(usuarioRequestDto.nome());
        usuarioTemp.setEmail(usuarioRequestDto.email());
        return UsuarioMapper.toDto(usuarioRepository.save(usuarioTemp));
    }

    //Delete com where
    public void deleteById(Long id){
        findById(id);
        usuarioRepository.deleteById(id);
    }

    //Update do email
    public UsuarioResponseDto updateEmail(Long id, UsuarioEmailRequestDto emailDto){
        Usuario usuarioTemp = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        usuarioTemp.setEmail(emailDto.email());
        return UsuarioMapper.toDto(usuarioRepository.save(usuarioTemp));
    }
}
