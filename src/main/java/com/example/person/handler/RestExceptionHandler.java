package com.example.person.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(CouldNotFindPersonException.class)
    public ExceptionFilters personException(final CouldNotFindPersonException ex) {
        return ExceptionFilters.builder()
                .title("Couldn't find any relative for the request")
                .status(BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .details(ex.getMessage())
                .build();
    }
}
