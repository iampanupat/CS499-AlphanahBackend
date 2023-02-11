package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.amazonaws.AmazonServiceException;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setFrom("Alphanah");
        response.setError(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    private final String alphanah = "Alphanah";

    // Handle function exception from this backend application
    @ExceptionHandler(AlphanahBaseException.class)
    public ResponseEntity<ErrorResponse> handleAlphanahBaseException(AlphanahBaseException e) {
        return handleException(e, alphanah);
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<ErrorResponse> handleAmazonServiceException(AmazonServiceException exception) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(exception.getStatusCode());
        response.setFrom("Amazon");
        response.setError(exception.getErrorMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(exception.getStatusCode()));
    }

    private final String springFramework = "Spring Framework";

    // Handle http request method not allowed
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return handleException(e, springFramework, HttpStatus.valueOf(e.getStatusCode().value()));
    }

    // Handle json invalid syntax
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return handleException(e, springFramework);
    }

    private ResponseEntity<ErrorResponse> handleException(Exception e, String owner) {
        return handleException(e, owner, HttpStatus.EXPECTATION_FAILED);
    }

    private ResponseEntity<ErrorResponse> handleException(Exception e, String owner, HttpStatus httpStatus) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(httpStatus.value());
        response.setFrom(owner);
        response.setError(e.getMessage());
        return new ResponseEntity<>(response, httpStatus);
    }

    @Data
    public static class ErrorResponse {
        private String timestamp = DateTime.now().toString();
        private int status;
        private String from;
        private String error;
    }

}
