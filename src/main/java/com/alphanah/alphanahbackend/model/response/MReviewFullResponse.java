package com.alphanah.alphanahbackend.model.response;

import lombok.Data;

import java.util.List;

@Data
public class MReviewFullResponse extends MReviewBaseResponse {
    private List<MImageBaseResponse> images;
    private MProductBaseResponse product;
}
