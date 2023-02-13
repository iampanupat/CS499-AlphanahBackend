package com.alphanah.alphanahbackend.model;

import lombok.Getter;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {

    private String timestamp = DateTime.now().toString();
    private String from;
    private Integer status;
    private String error;

    public ErrorResponse(HttpStatus httpStatus, String from, String error) {
        this.status = httpStatus.value();
        this.from = from;
        this.error = error;
    }

}