package com.aula.apibiblioteca.controller;

import com.aula.apibiblioteca.dto.UsuarioEmailRequestDto;
import com.aula.apibiblioteca.dto.UsuarioRequestDto;
import com.aula.apibiblioteca.dto.UsuarioResponseDto;
import com.aula.apibiblioteca.model.Usuario;
import com.aula.apibiblioteca.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8080/usuarios
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> findAll(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> findById(@PathVariable Long id){
        try{
            UsuarioResponseDto usuarioResponseDto = usuarioService.findById(id);
            return ResponseEntity.ok(usuarioResponseDto);
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid UsuarioRequestDto usuarioRequestDto, BindingResult result){

        if(result.hasErrors()){
            Map<String, String> erros = new HashMap<>();
            for(var error : result.getFieldErrors()){
                erros.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(erros);
        }

        var userCreated = usuarioService.save(usuarioRequestDto);
        return ResponseEntity.status(201).body(userCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> update(@PathVariable Long id, @RequestBody @Valid UsuarioRequestDto usuarioRequestDto){
        try{
            UsuarioResponseDto usuarioResponseDto = usuarioService.update(id, usuarioRequestDto);
            return ResponseEntity.ok(usuarioResponseDto);
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try{
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/update-email")
    public ResponseEntity<UsuarioResponseDto> updateEmail(@PathVariable Long id, @RequestBody @Valid UsuarioEmailRequestDto emailDto){
        try{
            UsuarioResponseDto usuarioResponseDto = usuarioService.updateEmail(id, emailDto);
            return ResponseEntity.ok(usuarioResponseDto);
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
