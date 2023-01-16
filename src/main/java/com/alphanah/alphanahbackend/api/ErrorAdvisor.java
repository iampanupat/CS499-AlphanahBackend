package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.amazonaws.AmazonServiceException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorAdvisor {

    private final String alphanah = "Alphanah";

    // Handle function exception from this backend application
    @ExceptionHandler(AlphanahBaseException.class)
    public ResponseEntity<ErrorResponse> handleAlphanahBaseException(AlphanahBaseException e) {
        return handleException(e, alphanah);
    }

    private final String amazon = "Amazon";

    // Handle amazon web services exception
    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<ErrorResponse> handleAmazonServiceException(AmazonServiceException e) {
        return handleException(e, amazon, HttpStatus.valueOf(e.getStatusCode()));
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
        private LocalDateTime timestamp = LocalDateTime.now();
        private int status;
        private String from;
        private String error;
    }

}
