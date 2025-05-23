package com.example.diplom.dto.response;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionResponse {

    private String message;

    private LocalDateTime timestamp = LocalDateTime.now();

    public ExceptionResponse(String message) {
        this.message = message;
    }
}
