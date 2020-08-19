package com.example.microservices.loginregisteruser.exceptionhandler;


import com.example.microservices.loginregisteruser.exception.ErrorResponse;
import com.example.microservices.loginregisteruser.exception.ExistingUserException;
import com.example.microservices.loginregisteruser.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
    private String INCORRECT_REQUEST = "INCORRECT_REQUEST";
    private String BAD_REQUEST = "BAD_REQUEST";
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException
            (ResourceNotFoundException ex, WebRequest request)
    {
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ExistingUserException.class)
    public final ResponseEntity<ErrorResponse> handleExistingUserException
            (ExistingUserException ex, WebRequest request)
    {
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}