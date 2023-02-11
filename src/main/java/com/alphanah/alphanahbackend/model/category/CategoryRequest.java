package com.alphanah.alphanahbackend.model.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequest {

    @NotBlank(message = "Category name cannot be null or empty.")
    @Size(max = 120, message = "Category name cannot be longer than {max} characters.")
    private String name;

}
