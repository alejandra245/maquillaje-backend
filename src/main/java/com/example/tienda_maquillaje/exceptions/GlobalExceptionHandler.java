package com.example.tienda_maquillaje.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que maneja globalmente las excepciones en la aplicación.
 * 
 * Esta clase captura y procesa las excepciones comunes relacionadas con la validación
 * para proporcionar respuestas coherentes y comprensibles a los usuarios.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja las excepciones de validación que ocurren cuando un argumento de método
     * no es válido.
     * 
     * @param ex La excepción {@link MethodArgumentNotValidException}.
     * @return Un mapa con los campos inválidos y sus mensajes de error, junto con un estado HTTP 400 (Bad Request).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Crear un mapa para almacenar los errores de validación
        Map<String, String> errors = new HashMap<>();
        // Iterar sobre los errores de campo y agregarlos al mapa
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        // Retornar los errores con el estado HTTP 400
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja las excepciones de violación de restricciones que ocurren cuando las validaciones
     * a nivel de entidad fallan.
     * 
     * @param ex La excepción {@link ConstraintViolationException}.
     * @return Un mapa con los campos que violaron restricciones y sus mensajes de error,
     *         junto con un estado HTTP 400 (Bad Request).
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        // Crear un mapa para almacenar los errores de validación
        Map<String, String> errors = new HashMap<>();
        // Iterar sobre las violaciones de restricciones y agregarlas al mapa
        ex.getConstraintViolations().forEach(violation -> {
            String field = violation.getPropertyPath().toString(); // Campo que causó la violación
            String message = violation.getMessage();              // Mensaje de error
            errors.put(field, message);
        });
        // Retornar los errores con el estado HTTP 400
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
