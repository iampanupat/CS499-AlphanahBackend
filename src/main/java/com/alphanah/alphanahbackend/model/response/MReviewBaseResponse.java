package com.alphanah.alphanahbackend.model.response;

import lombok.Data;

@Data
public class MReviewBaseResponse {
    private String uuid;
    private String header;
    private String message;
    private String rating;
    private String creatorUuid;
}
