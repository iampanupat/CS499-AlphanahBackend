package com.alphanah.alphanahbackend.model.product;

import com.alphanah.alphanahbackend.model.image.ImageResponseM1;
import com.alphanah.alphanahbackend.model.product_option.ProductOptionResponseM1;
import com.alphanah.alphanahbackend.model.review.ReviewResponseM2;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponseM2 extends ProductResponseM1 {
    private Double minPrice;
    private Double maxPrice;
    private List<ProductOptionResponseM1> options;
    private List<ImageResponseM1> images;
    private Double reviewScore;
    private List<ReviewResponseM2> reviews;
}
