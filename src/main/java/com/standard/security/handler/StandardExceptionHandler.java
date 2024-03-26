package com.standard.security.handler;

import com.google.common.base.Throwables;
import com.standard.domain.Error;
import com.standard.domain.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.Calendar;
import java.util.UUID;

@ControllerAdvice
public class StandardExceptionHandler {

    
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Error> validationsErrorHandler(ConstraintViolationException ex, HttpServletRequest request) {
        
        Error error = new Error();
        error.setReference(UUID.randomUUID().toString());
        error.setTarget(request.getRequestURI());
        error.setTimestamp(Calendar.getInstance().getTime());
        error.setMessage(ex.getMessage());
        
        ex.getConstraintViolations().forEach(constraintViolation -> {
                ErrorDetails detailsItem = new ErrorDetails();
                detailsItem.setTarget(constraintViolation.getPropertyPath().toString());
                detailsItem.setCode("400");
                detailsItem.setMessage(constraintViolation.getMessage() + ": " + constraintViolation.getPropertyPath().toString());
                error.getDetails().add(detailsItem);
        });
        
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Error> handleBindException(BindException ex, HttpServletRequest request) {

        Error error = new Error();
        error.setReference(UUID.randomUUID().toString());
        error.setTarget(request.getRequestURI());
        error.setTimestamp(Calendar.getInstance().getTime());

        ErrorDetails detailsItem = new ErrorDetails();
        detailsItem.setTarget("rootCause");
        detailsItem.setCode(ex.getClass().getName());
        detailsItem.setMessage(Throwables.getRootCause(ex).getMessage());
        error.getDetails().add(detailsItem);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Error> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {

        Error error = new Error();
        error.setReference(UUID.randomUUID().toString());
        error.setTarget(request.getRequestURI());
        error.setTimestamp(Calendar.getInstance().getTime());

        ErrorDetails detailsItem = new ErrorDetails();
        detailsItem.setTarget("rootCause");
        detailsItem.setCode(ex.getClass().getName());
        detailsItem.setMessage(Throwables.getRootCause(ex).getMessage());
        error.getDetails().add(detailsItem);
        
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    
    @ExceptionHandler({AuthenticationException.class })
    public ResponseEntity<Error> handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {

        Error error = new Error();
        error.setReference(UUID.randomUUID().toString());
        error.setTarget(request.getRequestURI());
        error.setTimestamp(Calendar.getInstance().getTime());

        ErrorDetails detailsItem = new ErrorDetails();
        detailsItem.setTarget("rootCause");
        detailsItem.setCode(ex.getClass().getName());
        detailsItem.setMessage(Throwables.getRootCause(ex).getMessage());
        error.getDetails().add(detailsItem);

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleEntityNotFoundException() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
    
