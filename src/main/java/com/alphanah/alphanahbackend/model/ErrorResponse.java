package com.alphanah.alphanahbackend.model;

import com.alphanah.alphanahbackend.utility.DateUtils;
import com.alphanah.alphanahbackend.utility.Env;
import lombok.Getter;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
public class ErrorResponse {

    private String timestamp = DateUtils.timeZoneConverter(new Date(), Env.bangkokZone);
    private String from;
    private Integer status;
    private String error;

    public ErrorResponse(HttpStatus httpStatus, String from, String error) {
        this.status = httpStatus.value();
        this.from = from;
        this.error = error;
    }

}