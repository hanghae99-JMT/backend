package com.jmt.v1.config.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.ExceptionUtils;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private int status;
    private String message;
    private String error;

    public ErrorResponse(HttpStatus httpStatus, String message){
        this.status = httpStatus.BAD_REQUEST.value();
        this.error = httpStatus.BAD_REQUEST.name();

        this.message = message;
    }

    public ErrorResponse(RuntimeException runtimeException){
        this.status = HttpStatus.BAD_REQUEST.value();
        this.error = HttpStatus.BAD_REQUEST.name();

        this.message = runtimeException.getMessage();
    }

    public ErrorResponse(MethodArgumentNotValidException methodArgumentNotValidException){

        this.status = HttpStatus.BAD_REQUEST.value();
        this.error = HttpStatus.BAD_REQUEST.name();

        var error = methodArgumentNotValidException.getBindingResult()
                .getFieldErrors().stream().findFirst().get();

        this.message = error.getField() + " : " + error.getDefaultMessage();

    }
}
