package com.aula.apibiblioteca.service;

import com.aula.apibiblioteca.dto.UsuarioEmailRequestDto;
import com.aula.apibiblioteca.dto.UsuarioRequestDto;
import com.aula.apibiblioteca.dto.UsuarioResponseDto;
import com.aula.apibiblioteca.enums.Role;
import com.aula.apibiblioteca.mapper.UsuarioMapper;
import com.aula.apibiblioteca.model.Usuario;
import com.aula.apibiblioteca.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Select sem Where
    public Page<UsuarioResponseDto> findAll(Pageable pagination){
        return usuarioRepository.findAll(pagination).map((u) -> UsuarioMapper.toDto(u));
    }

    //SELECT * FROM WHERE id=?
    public UsuarioResponseDto findById(Long id){
        var usuario = usuarioRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Usuário não Encontrado"));
        return UsuarioMapper.toDto(usuario);
    }

    //Insert
    public UsuarioResponseDto save(UsuarioRequestDto usuarioRequestDto){
        var usuario = UsuarioMapper.toEntity(usuarioRequestDto);
        return UsuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    //Update *
    public UsuarioResponseDto update(Long id, UsuarioRequestDto usuarioRequestDto){
        Usuario usuarioTemp = usuarioRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Usuário não Encontrado"));
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
        Usuario usuarioTemp = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não Encontrado"));
        usuarioTemp.setEmail(emailDto.email());
        return UsuarioMapper.toDto(usuarioRepository.save(usuarioTemp));
    }

    @Transactional
    public UsuarioResponseDto cadastrar(UsuarioRequestDto usuarioRequestDto){
        if(usuarioRepository.existsByEmail(usuarioRequestDto.email())){
            throw new RuntimeException("Email já existe: " + usuarioRequestDto.email());
        }

        var passwordEncrypted = passwordEncoder.encode(usuarioRequestDto.senha());

        var usuario = new Usuario();
        usuario.setNome(usuarioRequestDto.nome());
        usuario.setEmail(usuarioRequestDto.email());
        usuario.setRegra(Role.valueOf(usuarioRequestDto.regra()));
        usuario.setSenha(passwordEncrypted);

        return UsuarioMapper.toDto(usuarioRepository.save(usuario));
    }
}
