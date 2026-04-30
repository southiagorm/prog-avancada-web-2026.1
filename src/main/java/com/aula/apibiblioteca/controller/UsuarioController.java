package com.aula.apibiblioteca.controller;

import com.aula.apibiblioteca.model.Usuario;
import com.aula.apibiblioteca.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        try{
            Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
        var userCreated = usuarioService.save(usuario);
        return ResponseEntity.status(201).body(userCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario){
        try{
            Usuario usuarioTemp = usuarioService.update(id, usuario);
            return ResponseEntity.ok(usuarioTemp);
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
    public ResponseEntity<Usuario> updateEmail(@PathVariable Long id, @RequestBody Map<String, String> map){
        try{
            System.out.println(map.get("email"));
            Usuario usuarioTemp = usuarioService.updateEmail(id, map.get("email"));
            return ResponseEntity.ok(usuarioTemp);
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
