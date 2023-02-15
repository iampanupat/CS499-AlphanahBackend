package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.ErrorResponse;
import com.alphanah.alphanahbackend.utility.Env;
import com.amazonaws.AmazonServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorResponse response = new ErrorResponse(httpStatus, Env.ALPHANAH, exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(response, httpStatus);
    }

    // Handle function exception from this backend application
    @ExceptionHandler(AlphanahBaseException.class)
    public ResponseEntity<ErrorResponse> handleAlphanahBaseException(AlphanahBaseException e) {
        return handleException(e, Env.ALPHANAH);
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<ErrorResponse> handleAmazonServiceException(AmazonServiceException exception) {
        HttpStatus httpStatus = HttpStatus.valueOf(exception.getStatusCode());
        ErrorResponse response = new ErrorResponse(httpStatus, Env.AMAZON, exception.getErrorMessage());
        return new ResponseEntity<>(response, httpStatus);
    }

    // Handle http request method not allowed
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return handleException(e, Env.SPRING_FRAMEWORK, HttpStatus.valueOf(e.getStatusCode().value()));
    }

    // Handle json invalid syntax
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return handleException(e, Env.SPRING_FRAMEWORK);
    }

    private ResponseEntity<ErrorResponse> handleException(Exception e, String owner) {
        return handleException(e, owner, HttpStatus.EXPECTATION_FAILED);
    }

    private ResponseEntity<ErrorResponse> handleException(Exception e, String owner, HttpStatus httpStatus) {
        ErrorResponse response = new ErrorResponse(httpStatus, owner, e.getMessage());
        return new ResponseEntity<>(response, httpStatus);
    }

}