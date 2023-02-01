package com.alphanah.alphanahbackend.model.response;

import lombok.Data;

@Data
public class MProductCategoryFullResponse {
    private String uuid;
    private MProductBaseResponse product;
    private MCategoryBaseResponse category;
}
