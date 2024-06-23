package com.br.fatec.inventarioproduto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.br.fatec.inventarioproduto.dto.ErrorField;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ErrorField>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ErrorField> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(new ErrorField(fieldName, errorMessage));
        });
        return ResponseEntity.badRequest().body(errors);
    }

    // Outros manipuladores de exceção podem ser adicionados aqui conforme necessário
}