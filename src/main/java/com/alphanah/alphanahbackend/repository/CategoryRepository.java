package com.alphanah.alphanahbackend.repository;

import com.alphanah.alphanahbackend.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, String> {

    boolean existsByName(String categoryName);

    List<Category> findAllByParentCategoryIsNull();

}
