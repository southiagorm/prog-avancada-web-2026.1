package com.aula.apibiblioteca.filter;

import com.aula.apibiblioteca.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // 1. Lê o header Authorization
        final String authHeader = request.getHeader("Authorization");

        // 2. Sem token ou formato errado → passa adiante sem autenticar
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extrai o token removendo "Bearer "
        final String jwt = authHeader.substring(7);

        // 4. Extrai o username (email) do payload do token
        final String userEmail = jwtService.extractUsername(jwt);

        // 5. Só processa se tem email e o contexto ainda está vazio
        if (userEmail != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            // 6. Busca o usuário no banco
            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(userEmail);

            // 7. Valida assinatura e expiração
            if (jwtService.isTokenValid(jwt, userDetails)) {

                // 8. Cria o objeto de autenticação
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                // 9. Adiciona detalhes da requisição
                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                // 10. Registra no contexto — usuário autenticado a partir daqui
                SecurityContextHolder.getContext()
                        .setAuthentication(authToken);
            }
        }

        // 11. Continua a cadeia de filtros
        filterChain.doFilter(request, response);
    }
}
