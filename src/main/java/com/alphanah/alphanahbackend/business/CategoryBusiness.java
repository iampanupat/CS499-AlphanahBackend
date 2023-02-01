package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.Category;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.category.MCreateCategoryRequest;
import com.alphanah.alphanahbackend.model.category.MUpdateCategoryRequest;
import com.alphanah.alphanahbackend.model.response.MCategoryBaseResponse;
import com.alphanah.alphanahbackend.model.response.MCategoryFullResponse;
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

    @Autowired
    private AccountUtils accountUtils;

    public List<MCategoryFullResponse> getAllCategories() {
        List<MCategoryFullResponse> responses = new ArrayList<>();
        List<Category> categories = service.getAll();
        for (Category category : categories)
            responses.add(category.toMCategoryFullResponse());
        return responses;
    }

    public MCategoryFullResponse getCategory(UUID uuid) throws AlphanahBaseException {
        return service.get(uuid).toMCategoryFullResponse();
    }

    public MCategoryBaseResponse createCategory(String token, MCreateCategoryRequest request) throws AlphanahBaseException {
        return service.create(accountUtils.getAccount(token).getUuid(), request.getName()).toMCategoryBaseResponse();
    }

    public MCategoryBaseResponse updateCategory(String token, UUID uuid, MUpdateCategoryRequest request) throws AlphanahBaseException {
        return service.update(accountUtils.getAccount(token).getUuid(), uuid, request.getName()).toMCategoryBaseResponse();
    }

    public void deleteCategory(String token, UUID uuid) throws AlphanahBaseException {
        service.delete(accountUtils.getAccount(token).getUuid(), uuid);
    }

}
