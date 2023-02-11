package com.alphanah.alphanahbackend.model.category;

import com.alphanah.alphanahbackend.model.product.ProductResponseM2;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponseM3 extends CategoryResponseM2 {
    private List<ProductResponseM2> products;
}
