package com.meli.w4.desafiospring.advice;


import exception.RepositoryException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class PersistenceExceptionAdvice {

    @ExceptionHandler(value = RepositoryException.class)
    protected ResponseEntity<Object> handlePersistencia(RepositoryException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }


    @ExceptionHandler(value = NullPointerException.class)
    protected ResponseEntity<Object> handleNullPointer(NullPointerException ex, WebRequest request) {
        //String bodyOfResponse = ex.getMessage();
        return ResponseEntity.badRequest().body("Item não encontrado!");
    }

    @ExceptionHandler(value = ArithmeticException.class)
    protected ResponseEntity<Object> erroCalculo(ArithmeticException ex, WebRequest request) {
        //String bodyOfResponse = ex.getMessage();
        return ResponseEntity.badRequest().body("Operação inválida!");
    }

}
