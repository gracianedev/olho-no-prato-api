package com.gfs.olhonoprato.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // 1. Torna esta classe um "supervisor" global de exceções
public class GlobalExceptionHandler {

    // 2. Este método é especificamente para capturar a EmailJaCadastradoException
    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<ApiErrorResponse> handleEmailJaCadastrado(EmailJaCadastradoException ex) {

        // 3. Cria nosso objeto de erro padronizado
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );

        // 4. Retorna uma resposta HTTP completa, com o corpo (nosso objeto) e o status correto
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}