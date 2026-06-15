package com.aula.apibiblioteca.exception;

import com.aula.apibiblioteca.dto.ExceptionResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandleException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleEntityNotFoundException(EntityNotFoundException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("mensagem", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseDto(
                "404",
                errors,
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDto> handleValidation(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((e) -> {
            errors.put(e.getField(), e.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(new ExceptionResponseDto(
                "400",
                errors,
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(EmailExisteException.class)
    public ResponseEntity<ExceptionResponseDto> handleEmailExisteException(EmailExisteException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ExceptionResponseDto(
                        HttpStatus.CONFLICT.toString(),
                        Map.of("message", ex.getMessage()),
                        LocalDateTime.now()));
    }
}
