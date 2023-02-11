package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Category;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.CategoryException;
import com.alphanah.alphanahbackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    private final int CATEGORY_NAME_MAX_LENGTH = 120;

    public List<Category> getAll() {
        return (List<Category>) repository.findAll();
    }

    public Category get(UUID uuid) throws AlphanahBaseException {
        if (Objects.isNull(uuid))
            throw CategoryException.getWithNullUuid();

        Optional<Category> optional = repository.findById(uuid.toString());
        if (optional.isEmpty())
            throw CategoryException.getNullObject();

        return optional.get();
    }

    public Category create(UUID parentUuid, String name) throws AlphanahBaseException {
        if (Objects.isNull(name))
            throw CategoryException.createWithNullName();

        if (name.isEmpty())
            throw CategoryException.createWithEmptyName();

        if (name.length() > CATEGORY_NAME_MAX_LENGTH)
            throw CategoryException.createWithMaxLengthName();

        name = name.toLowerCase();
        Category parentCategory = null;
        if (parentUuid != null) {
            try {
                 parentCategory = this.get(parentUuid);
            } catch (AlphanahBaseException exception) {
                throw CategoryException.createWithNullParentObject();
            }

            for (Category category : parentCategory.getChildCategories()) {
                if (category.getName().equals(name))
                    throw CategoryException.createDuplicateName();
            }
        } else {
            for (Category category : repository.findAllByParentCategoryIsNull()) {
                if (category.getName().equals(name))
                    throw CategoryException.createDuplicateName();
            }
        }

        Category entity = new Category();
        entity.setName(name);
        entity.setParentCategory(parentCategory);
        return repository.save(entity);
    }

}
