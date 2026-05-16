package com.aula.apibiblioteca.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ExceptionResponseDto(String status,
                                   Map<String, String> errors,
                                   LocalDateTime localDateTime) {
}
