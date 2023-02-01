package com.alphanah.alphanahbackend.model.review;

import lombok.Data;

@Data
public class MCreateReviewRequest {
    private String header;
    private String message;
    private int rating;
}
