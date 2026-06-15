package com.aula.apibiblioteca.controller;

import com.aula.apibiblioteca.dto.AuthResponseDto;
import com.aula.apibiblioteca.dto.LoginRequestDto;
import com.aula.apibiblioteca.dto.UsuarioRequestDto;
import com.aula.apibiblioteca.service.AuthService;
import com.aula.apibiblioteca.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService userService;
    private final AuthService authService;

    @PostMapping("/cadastrar")
    public ResponseEntity<AuthResponseDto> cadastrar(@RequestBody @Valid UsuarioRequestDto usuarioRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.cadastrar(usuarioRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(
            @RequestBody @Valid LoginRequestDto request) {

        AuthResponseDto response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
