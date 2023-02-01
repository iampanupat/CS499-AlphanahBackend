package com.alphanah.alphanahbackend.model.product.option;

import lombok.Data;

@Data
public class MUpdateProductOptionRequest {
    private String name;
    private Double price;
    private Integer quantity;
}
