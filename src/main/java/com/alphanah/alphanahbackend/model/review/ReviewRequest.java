package com.alphanah.alphanahbackend.model.review;

import lombok.Data;

@Data
public class ReviewRequest {
    private String message;
    private int rating = -1;
}
