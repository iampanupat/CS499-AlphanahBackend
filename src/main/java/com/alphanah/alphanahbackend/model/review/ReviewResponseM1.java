package com.alphanah.alphanahbackend.model.review;

import com.alphanah.alphanahbackend.model.account.AccountResponseM1;
import lombok.Data;

@Data
public class ReviewResponseM1 {
    private String reviewUUID;
    private String message;
    private String rating;
    private String createDate;
    private AccountResponseM1 creator;
}
