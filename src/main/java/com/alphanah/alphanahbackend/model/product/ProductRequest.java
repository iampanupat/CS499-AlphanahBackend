package com.alphanah.alphanahbackend.model.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Product name cannot be null or empty.")
    @Size(max = 120, message = "Product name cannot be longer than {max} characters.")
    private String name;

    @NotBlank(message = "Product description cannot be null or empty.")
    @Size(max = 255, message = "Product description cannot be longer than {max} characters.")
    private String description;

}
