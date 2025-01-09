package com.example.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to handle cases where a book is not found in the system.
 * Annotated with @ResponseStatus to automatically return a 404 Not Found HTTP status code
 * when this exception is thrown.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND) // Maps this exception to a 404 Not Found HTTP status.
public class BookNotFoundException extends RuntimeException {

    /**
     * Constructor for BookNotFoundException.
     * Accepts a custom error message that describes the reason for the exception.
     *
     * @param message Detailed error message for the exception.
     */
    public BookNotFoundException(String message) {
        super(message);
    }
}
