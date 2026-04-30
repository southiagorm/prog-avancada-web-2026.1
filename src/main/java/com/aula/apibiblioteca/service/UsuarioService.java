package com.aula.apibiblioteca.service;

import com.aula.apibiblioteca.model.Usuario;
import com.aula.apibiblioteca.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Select sem Where
    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    //SELECT * FROM WHERE id=?
    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
    }

    //Insert
    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    //Update *
    public Usuario update(Long id, Usuario usuario){
        Usuario usuarioTemp = usuarioRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        usuarioTemp.setNome(usuario.getNome());
        usuarioTemp.setEmail(usuario.getEmail());
        return usuarioRepository.save(usuarioTemp);
    }

    //Delete com where
    public void deleteById(Long id){
        findById(id);
        usuarioRepository.deleteById(id);
    }

    //Update do email
    public Usuario updateEmail(Long id, String email){
        Usuario usuarioTemp = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        usuarioTemp.setEmail(email);
        return usuarioRepository.save(usuarioTemp);
    }
}
