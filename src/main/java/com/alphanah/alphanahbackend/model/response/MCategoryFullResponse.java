package com.alphanah.alphanahbackend.model.response;

import lombok.Data;

import java.util.List;

@Data
public class MCategoryFullResponse extends MCategoryBaseResponse {
    private List<MProductWithoutCategoryResponse> products;
}
