package com.library.management.exceptionhandler;

import com.library.management.exceptions.BookNotFoundException;
import com.library.management.exceptions.DuplicateBooksException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateBooksException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleDuplicateBookException(DuplicateBooksException ex) {
        Map<String, String> details = new HashMap<>();
        details.put("bookId", ex.getBookId());

        return ErrorResponseBuilder.buildErrorResponse(
                HttpStatus.CONFLICT,
                "Duplicate Book",
                ex.getMessage(),
                details
        );
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleBookNotFoundException(BookNotFoundException ex) {
        return ErrorResponseBuilder.buildErrorResponse(
                HttpStatus.NOT_FOUND,
                "Book Not Found",
                ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        });

        return ErrorResponseBuilder.buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Validation Error",
                "Invalid request data",
                fieldErrors
        );
    }
}
