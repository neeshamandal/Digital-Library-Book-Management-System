package com.library.management.exceptionhandler;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ErrorResponseBuilder {
    public static Map<String, Object> buildErrorResponse(
            HttpStatus status,
            String errorType,
            String message,
            Map<String, String> details) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", errorType);
        response.put("message", message);

        if (details != null && !details.isEmpty()) {
            response.put("details", details);
        }

        return response;
    }

    public static Map<String, Object> buildErrorResponse(
            HttpStatus status,
            String errorType,
            String message) {
        return buildErrorResponse(status, errorType, message, null);
    }
}
