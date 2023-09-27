package br.com.vollmed.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity invalidData(MethodArgumentNotValidException ex) {
        var responseErroValidacao = ex.getFieldErrors().stream().map(ResponseErroValidacaoCampos::new).toList();
        return ResponseEntity.badRequest().build();
    }

    private record ResponseErroValidacaoCampos(String campo, String msg) {
        public ResponseErroValidacaoCampos(FieldError campoInvalidado) {
            this(campoInvalidado.getField(), campoInvalidado.getDefaultMessage());
        }
    }
}
