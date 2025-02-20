package com.standard.security.handler;


import com.standard.domainOld.Error;
import com.standard.domainOld.ErrorDetails;
import com.standard.security.exceptions.BrandNotFoundException;
import com.standard.security.exceptions.CategoryNotFoundException;
import com.standard.security.exceptions.DomainNotFoundException;
import com.standard.security.exceptions.MeasureNotFoundException;
import com.standard.security.exceptions.PayBackNotFoundException;
import com.standard.security.exceptions.PaymentMethodNotFoundException;
import com.standard.security.exceptions.SubcategoryNotFoundException;
import com.standard.security.exceptions.WithdrawalNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.nio.file.ProviderNotFoundException;
import java.util.Calendar;
import java.util.UUID;

@ControllerAdvice
public class StandardExceptionHandler {

    public static final String ROOT_CAUSE = "rootCause";

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
        detailsItem.setTarget(ROOT_CAUSE);
        detailsItem.setCode(ex.getClass().getName());
        detailsItem.setMessage(ex.getMessage()); 
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
        detailsItem.setTarget(ROOT_CAUSE);
        detailsItem.setCode(ex.getClass().getName());
        detailsItem.setMessage(ex.getMessage());
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
        detailsItem.setTarget(ROOT_CAUSE);
        detailsItem.setCode(ex.getClass().getName());
        detailsItem.setMessage(ex.getMessage());
        error.getDetails().add(detailsItem);

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({BrandNotFoundException.class, CategoryNotFoundException.class, DomainNotFoundException.class, MeasureNotFoundException.class, PayBackNotFoundException.class,
            PaymentMethodNotFoundException.class, ProviderNotFoundException.class, SubcategoryNotFoundException.class, WithdrawalNotFoundException.class})
    public ResponseEntity<Void> handleEntityNotFoundException() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }






}
    
