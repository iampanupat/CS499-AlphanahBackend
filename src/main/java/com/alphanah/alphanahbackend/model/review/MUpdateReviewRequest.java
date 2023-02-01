package com.alphanah.alphanahbackend.model.review;

import lombok.Data;

@Data
public class MUpdateReviewRequest {
    private String header;
    private String message;
    private int rating;
}
