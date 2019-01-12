package com.grv.restproject.socialNetwork.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class SocialNetworkExceptionResponseHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        ExceptionResponseBean exceptionResponseBean = new ExceptionResponseBean(Instant.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponseBean, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponseBean exceptionResponseBean = new ExceptionResponseBean(Instant.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponseBean, HttpStatus.NOT_FOUND);
    }
}
