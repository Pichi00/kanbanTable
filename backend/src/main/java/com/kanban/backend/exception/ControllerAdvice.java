package com.kanban.backend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j //logger
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    String resourceNotFoundExceptionHandler(final ResourceNotFoundException exception){
        log.error(exception.getMessage());
        return exception.getMessage();
    }
}
