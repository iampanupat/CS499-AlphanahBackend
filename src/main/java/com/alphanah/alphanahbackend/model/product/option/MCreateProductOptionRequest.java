package com.alphanah.alphanahbackend.model.product.option;

import lombok.Data;

@Data
public class MCreateProductOptionRequest {
    private String name;
    private Double price;
    private Integer quantity;
}
