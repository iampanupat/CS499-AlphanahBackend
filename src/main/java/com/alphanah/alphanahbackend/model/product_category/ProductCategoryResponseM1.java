package com.alphanah.alphanahbackend.model.product_category;

import com.alphanah.alphanahbackend.model.category.CategoryResponseM1;
import com.alphanah.alphanahbackend.model.product.ProductResponseM1;
import lombok.Data;

@Data
public class ProductCategoryResponseM1 {
    private String productCategoryUUID;
    private ProductResponseM1 product;
    private CategoryResponseM1 category;
}
