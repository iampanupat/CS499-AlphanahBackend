package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.model.ErrorResponse;
import com.alphanah.alphanahbackend.utility.Env;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorAPI {

    @GetMapping("/unauthorized")
    public ResponseEntity<ErrorResponse> unauthorized() {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        ErrorResponse response = new ErrorResponse(httpStatus, Env.ALPHANAH, httpStatus.getReasonPhrase().toLowerCase());
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/access-denied")
    public ResponseEntity<ErrorResponse> accessDenied() {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        ErrorResponse response = new ErrorResponse(httpStatus, Env.ALPHANAH, httpStatus.getReasonPhrase().toLowerCase());
        return new ResponseEntity<>(response, httpStatus);
    }

}
