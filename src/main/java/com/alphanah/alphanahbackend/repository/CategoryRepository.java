package com.alphanah.alphanahbackend.repository;

import com.alphanah.alphanahbackend.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends CrudRepository<Category, UUID> {

    boolean existsByName(String categoryName);

    List<Category> findAllByParentCategoryIsNull();

}
