package com.example.library.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler to centralize and handle application-wide exceptions.
 * Uses @ControllerAdvice to apply to all controllers in the application.
 */
@ControllerAdvice
@Slf4j // Provides logging capabilities using Lombok's Slf4j annotation.
public class GlobalExceptionHandler {

    /**
     * Handles BookNotFoundException and constructs a custom response with an HTTP 404 status code.
     *
     * @param ex the exception instance.
     * @return ResponseEntity containing an error message and HTTP status.
     */
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBookNotFound(BookNotFoundException ex) {
        log.error("Book not found exception: {}", ex.getMessage()); // Logs the exception details.
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage()); // Creates an error response with the exception message.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse); // Returns 404 Not Found.
    }

    /**
     * Handles validation errors triggered by @Valid annotations on request bodies or parameters.
     *
     * @param ex the MethodArgumentNotValidException instance containing validation details.
     * @return ResponseEntity containing a map of field errors and HTTP 400 status.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("Validation error: {}", ex.getMessage()); // Logs the validation error details.
        // Collects all validation errors into a map with field names as keys and error messages as values.
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(),
                        fieldError -> fieldError.getDefaultMessage(),
                        (existing, replacement) -> existing)); // Handles duplicate keys.
        return ResponseEntity.badRequest().body(errors); // Returns 400 Bad Request with validation error details.
    }
}