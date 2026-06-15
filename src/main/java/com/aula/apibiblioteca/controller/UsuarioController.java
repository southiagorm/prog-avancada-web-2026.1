package com.aula.apibiblioteca.controller;

import com.aula.apibiblioteca.dto.UsuarioEmailRequestDto;
import com.aula.apibiblioteca.dto.UsuarioRequestDto;
import com.aula.apibiblioteca.dto.UsuarioResponseDto;
import com.aula.apibiblioteca.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
//http://localhost:8080/usuarios
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDto>> findAll(@PageableDefault(size = 10) Pageable pagination){

        return ResponseEntity.ok(usuarioService.findAll(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> findById(@PathVariable Long id){
        UsuarioResponseDto usuarioResponseDto = usuarioService.findById(id);
        return ResponseEntity.ok(usuarioResponseDto);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid UsuarioRequestDto usuarioRequestDto){
        var userCreated = usuarioService.save(usuarioRequestDto);
        return ResponseEntity.status(201).body(userCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> update(@PathVariable Long id, @RequestBody @Valid UsuarioRequestDto usuarioRequestDto){
        UsuarioResponseDto usuarioResponseDto = usuarioService.update(id, usuarioRequestDto);
        return ResponseEntity.ok(usuarioResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/update-email")
    public ResponseEntity<UsuarioResponseDto> updateEmail(@PathVariable Long id, @RequestBody @Valid UsuarioEmailRequestDto emailDto){
        UsuarioResponseDto usuarioResponseDto = usuarioService.updateEmail(id, emailDto);
        return ResponseEntity.ok(usuarioResponseDto);
    }
}
