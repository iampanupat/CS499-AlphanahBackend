package com.alphanah.alphanahbackend.model.category;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponseM2 extends CategoryResponseM1 {
    private CategoryResponseM1 parentCategory;
    private List<CategoryResponseM1> childCategories;
}
