package com.alphanah.alphanahbackend.model.review;

import com.alphanah.alphanahbackend.model.image.ImageResponseM1;
import com.alphanah.alphanahbackend.model.product.ProductResponseM1;
import lombok.Data;

import java.util.List;

@Data
public class ReviewResponseM3 extends ReviewResponseM2 {
    private ProductResponseM1 product;
}
