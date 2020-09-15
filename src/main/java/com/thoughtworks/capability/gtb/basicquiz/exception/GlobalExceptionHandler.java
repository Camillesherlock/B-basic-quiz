package com.thoughtworks.capability.gtb.basicquiz.exception;

import com.thoughtworks.capability.gtb.basicquiz.exception.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handle(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        ErrorMessage errorMessage = new ErrorMessage(Instant.now().toString(), 400, "Bad Request", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorMessage> handle(CommonException e) {
        String message = e.getMessage();
        ErrorMessage errorMessage = new ErrorMessage(Instant.now().toString(), 404, "Not Found", message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
