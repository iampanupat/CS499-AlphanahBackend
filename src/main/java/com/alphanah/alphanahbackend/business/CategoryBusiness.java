package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.Category;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.category.CategoryRequest;
import com.alphanah.alphanahbackend.model.category.CategoryResponseM2;
import com.alphanah.alphanahbackend.model.category.CategoryResponseM3;
import com.alphanah.alphanahbackend.service.CategoryService;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryBusiness {

    @Autowired
    private CategoryService service;

    public List<CategoryResponseM3> getAllCategories() throws AlphanahBaseException {
        List<CategoryResponseM3> responses = new ArrayList<>();
        List<Category> categories = service.findAllCategories();
        for (Category category: categories)
            responses.add(category.toCategoryResponseM3(null));
        return responses;
    }

    public CategoryResponseM3 getCategory(UUID uuid) throws AlphanahBaseException {
        Category response = service.findCategory(uuid);
        return response.toCategoryResponseM3(null);
    }

    public CategoryResponseM2 createCategory(UUID parentUuid, CategoryRequest request) throws AlphanahBaseException {
        Category response = service.createCategory(parentUuid, request.getName());
        return response.toCategoryResponseM2(null);
    }

}
