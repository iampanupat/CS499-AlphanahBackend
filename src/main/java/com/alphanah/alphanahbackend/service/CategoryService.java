package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Category;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.CategoryException;
import com.alphanah.alphanahbackend.repository.CategoryRepository;
import com.alphanah.alphanahbackend.utility.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAllCategories() {
        return (List<Category>) repository.findAll();
    }

    public Category findCategory(UUID categoryUuid) throws AlphanahBaseException {
        if (Objects.isNull(categoryUuid))
            throw CategoryException.cannotFindWithNullCategoryUuid();

        Optional<Category> optional = repository.findById(categoryUuid);
        if (optional.isEmpty())
            throw CategoryException.notFound();

        return optional.get();
    }

    public Category createCategory(UUID parentUuid, String name) throws AlphanahBaseException {
        if (Objects.isNull(name))
            throw CategoryException.cannotCreateWithNullName();

        if (name.isEmpty())
            throw CategoryException.cannotCreateWithEmptyName();

        if (name.length() > Env.CATEGORY_NAME_MAX_LENGTH)
            throw CategoryException.cannotCreateWithNameExceedMaxLength();

        Category parentCategory = null;
        name = name.toLowerCase();
        if (parentUuid != null) {
            parentCategory = this.findCategory(parentUuid);
            for (Category category : parentCategory.getChildCategories()) {
                if (category.getName().equals(name))
                    throw CategoryException.cannotCreateWithDuplicateName();
            }
        } else {
            for (Category category : repository.findAllByParentCategoryIsNull()) {
                if (category.getName().equals(name))
                    throw CategoryException.cannotCreateWithDuplicateName();
            }
        }

        Category entity = new Category();
        entity.setName(name);
        entity.setLevel(Objects.isNull(parentCategory) ? 0 : parentCategory.getLevel() + 1);
        entity.setParentCategory(parentCategory);
        return repository.save(entity);
    }

}
