package com.alphanah.alphanahbackend.model.product_option;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductOptionRequest {
    private String name;
    private Double price;
    private Integer quantity;
}
