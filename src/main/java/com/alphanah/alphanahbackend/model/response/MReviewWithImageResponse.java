package com.alphanah.alphanahbackend.model.response;

import lombok.Data;

import java.util.List;

@Data
public class MReviewWithImageResponse extends MReviewBaseResponse {
    List<MImageBaseResponse> images;
}
