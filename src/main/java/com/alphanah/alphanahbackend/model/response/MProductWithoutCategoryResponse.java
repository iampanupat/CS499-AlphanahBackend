package com.alphanah.alphanahbackend.model.response;

import lombok.Data;

import java.util.List;

@Data
public class MProductWithoutCategoryResponse extends MProductBaseResponse {
    private List<MProductOptionBaseResponse> options;
    private List<MImageBaseResponse> images;
    private List<MReviewWithImageResponse> reviews;
}
