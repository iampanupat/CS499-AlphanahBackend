package com.alphanah.alphanahbackend.model.response;

import lombok.Data;

@Data
public class MProductBaseResponse {
    private String uuid;
    private String name;
    private String description;
    private String creatorUuid;
}
