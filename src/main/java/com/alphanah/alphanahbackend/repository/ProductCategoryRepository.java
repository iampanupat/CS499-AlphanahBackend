package com.alphanah.alphanahbackend.repository;

import com.alphanah.alphanahbackend.entity.Category;
import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.entity.ProductCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, String> {

    Optional<ProductCategory> findByProductAndCategory(Product product, Category category);

    boolean existsByProductAndCategory(Product product, Category category);

}
