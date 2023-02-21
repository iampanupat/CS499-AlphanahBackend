package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Category;
import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.entity.ProductCategory;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.ProductCategoryException;
import com.alphanah.alphanahbackend.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    public List<ProductCategory> createProductCategory(UUID creatorUuid, UUID productUuid, UUID categoryUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ProductCategoryException.cannotCreateWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ProductCategoryException.cannotCreateWithNullProductUuid();

        if (Objects.isNull(categoryUuid))
            throw ProductCategoryException.cannotCreateWithNullCategoryUuid();

        Product product = productService.findProduct(productUuid);
        if (!Objects.isNull(product.getProductCategories()))
            throw ProductCategoryException.cannotCreateMultipleRelationship();

        Category category = categoryService.findCategory(categoryUuid);
        if (repository.existsByProductAndCategory(product, category))
            throw ProductCategoryException.cannotCreateDuplicateRelationship();

        if (!product.getCreatorUuid().equals(creatorUuid))
            throw ProductCategoryException.cannotCreateNotOwned();

        List<ProductCategory> entityList = new ArrayList<>();
        while (category != null) {
            if (!repository.existsByProductAndCategory(product, category)) {
                ProductCategory entity = new ProductCategory();
                entity.setProduct(product);
                entity.setCategory(category);
                entityList.add(entity);
            }
            category = category.getParentCategory();
        }
        return (List<ProductCategory>) repository.saveAll(entityList);
    }

    public void deleteProductCategory(UUID creatorUuid, UUID productUuid, UUID categoryUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ProductCategoryException.cannotDeleteWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ProductCategoryException.cannotDeleteWithNullProductUuid();

        if (Objects.isNull(categoryUuid))
            throw ProductCategoryException.cannotDeleteWithNullCategoryUuid();

        Product product = productService.findProduct(productUuid);
        Category category = categoryService.findCategory(categoryUuid);
        if (!product.getCreatorUuid().equals(creatorUuid))
            throw ProductCategoryException.cannotDeleteNotOwned();

        List<ProductCategory> entityList = new ArrayList<>();
        List<Category> categoryAndSubcategories = new ArrayList<>();
        categoryAndSubcategories.add(category);
        categoryAndSubcategories.addAll(category.getAllChild());
        for (Category currentCategory: categoryAndSubcategories) {
            Optional<ProductCategory> optional = repository.findByProductAndCategory(product, currentCategory);
            if (optional.isEmpty())
                continue;
            entityList.add(optional.get());
        }

        if (entityList.isEmpty())
            throw ProductCategoryException.cannotDeleteNullRelationship();

        repository.deleteAll(entityList);
    }

}