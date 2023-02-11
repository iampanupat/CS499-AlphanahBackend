package com.alphanah.alphanahbackend.model.product;

import com.alphanah.alphanahbackend.model.category.CategoryResponseM1;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponseM3 extends ProductResponseM2 {
    private List<CategoryResponseM1> categories;
}

