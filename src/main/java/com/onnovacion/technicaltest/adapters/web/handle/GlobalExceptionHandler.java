package com.onnovacion.technicaltest.adapters.web.handle;

import com.onnovacion.technicaltest.application.exception.DeleteAccountException;
import com.onnovacion.technicaltest.application.exception.Error;
import com.onnovacion.technicaltest.application.exception.InvalidMovementException;
import com.onnovacion.technicaltest.application.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.GenericJDBCException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handle(ConstraintViolationException ex) {
        List<Error> errors = new ArrayList<>();
        ex.getConstraintViolations().forEach(r -> {
            Error error = new Error();
            error.setCode(HttpStatus.BAD_REQUEST.name());
            error.setMessage(r.getMessage());
            errors.add(error);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity handle(PSQLException ex){
        Error error = new Error();
        error.setCode(HttpStatus.BAD_REQUEST.name());
        error.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handle(HttpMessageNotReadableException ex){
        Error error = new Error();
        error.setCode(HttpStatus.BAD_REQUEST.name());
        error.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handle(IllegalArgumentException ex){
        Error error = new Error();
        error.setCode(HttpStatus.BAD_REQUEST.name());
        error.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handle(ResourceNotFoundException ex){
        Error error = new Error();
        error.setCode(HttpStatus.NOT_FOUND.name());
        error.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InvalidMovementException.class)
    public ResponseEntity handle(InvalidMovementException ex){
        Error error = new Error();
        error.setCode(HttpStatus.BAD_REQUEST.name());
        error.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DeleteAccountException.class)
    public ResponseEntity handle(DeleteAccountException ex){
        Error error = new Error();
        error.setCode(HttpStatus.CONFLICT.name());
        error.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
