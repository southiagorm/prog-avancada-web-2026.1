package com.aula.apibiblioteca.service;

import com.aula.apibiblioteca.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UsuarioRepository userRepository;

    // Spring Security chama este método em dois momentos:
    //   1. No login — DaoAuthenticationProvider busca o usuário para comparar a senha
    //   2. Em cada requisição — JwtAuthFilter busca para validar o token
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return userRepository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Usuário não encontrado: " + username
                        )
                );
    }
}
