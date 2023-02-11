package com.alphanah.alphanahbackend.model.product_option;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductOptionRequest {

    @NotBlank(message = "Product Option name cannot be null or empty.")
    @Size(max = 120, message = "Product Option name cannot be longer than {max} characters.")
    private String name;

    @Min(value = 0, message = "Product Option price cannot be less than {value}.")
    @Max(value = 1000000, message = "Product Option price cannot be more than {value}.")
    private Double price = -1.0;

    @Min(value = 0, message = "Product Option quantity cannot be less than {value}.")
    @Max(value = 1000000, message = "Product Option quantity cannot be more than {value}.")
    private Integer quantity = -1;

}
