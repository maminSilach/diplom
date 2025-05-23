package com.example.diplom.handler;

import com.example.diplom.dto.response.ExceptionResponse;
import com.example.diplom.exception.NotFoundException;
import com.example.diplom.exception.NotUniqueException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotUniqueException.class)
    public ResponseEntity<ExceptionResponse> notUniqueException(NotUniqueException e) {
        log.error("Thrown error: ", e);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        createExceptionResponse(e.getMessage())
                );

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundException(NotFoundException e) {
        log.error("Thrown error: ", e);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        createExceptionResponse(e.getMessage())
                );
    }


    private ExceptionResponse createExceptionResponse(String message) {
        return new ExceptionResponse(message);
    }
}
