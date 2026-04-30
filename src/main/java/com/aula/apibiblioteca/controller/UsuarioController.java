package com.aula.apibiblioteca.controller;

import com.aula.apibiblioteca.model.Usuario;
import com.aula.apibiblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//http://localhost:8080/usuarios
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
        var userCreated = usuarioService.save(usuario);
        return ResponseEntity.status(201).body(userCreated);
    }
}
