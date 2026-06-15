package com.aula.apibiblioteca.exception;

public class EmailExisteException extends RuntimeException{
    public EmailExisteException(String email) {
        super("O e-mail já existe: " + email);
    }
}
