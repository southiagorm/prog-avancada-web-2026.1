package com.aula.apibiblioteca.service;

import com.aula.apibiblioteca.dto.AuthResponseDto;
import com.aula.apibiblioteca.dto.LoginRequestDto;
import com.aula.apibiblioteca.exception.EmailExisteException;
import com.aula.apibiblioteca.model.Usuario;
import com.aula.apibiblioteca.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final UsuarioRepository userRepository;
    private final JwtService            jwtService;

    public AuthResponseDto login(LoginRequestDto request) {

        // ETAPA 1 — Autentica as credenciais
        // O AuthenticationManager delega ao DaoAuthenticationProvider que:
        //   a) chama loadUserByUsername(email) → busca no banco
        //   b) chama BCrypt.matches(senhaDigitada, hashDoBanco)
        // Se falhar → lança BadCredentialsException → 401 automático
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        } catch (AuthenticationException ex) {
            throw ex; // GlobalExceptionHandler captura e retorna 401
        }

        // ETAPA 2 — Credenciais corretas: busca os dados completos do usuário
        // Necessário para ter acesso ao id, nome, role etc.
        Usuario user = (Usuario) userRepository.findByEmail(request.email())
                .orElseThrow(() -> new EmailExisteException(request.email()));

        // ETAPA 3 — Gera o JWT para o usuário autenticado
        String token = jwtService.generateToken(user);

        // ETAPA 4 — Retorna o DTO com o token
        return AuthResponseDto.builder()
                .id(user.getId())
                .nome(user.getNome())
                .email(user.getEmail())
                .role(user.getRegra().name())
                .token(token)
                .build();
    }
}
