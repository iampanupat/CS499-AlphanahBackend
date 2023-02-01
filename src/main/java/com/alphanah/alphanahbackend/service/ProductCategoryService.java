package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Category;
import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.entity.ProductCategory;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.ProductCategoryException;
import com.alphanah.alphanahbackend.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    public ProductCategory create(UUID creatorUuid, UUID productUuid, UUID categoryUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ProductCategoryException.createWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ProductCategoryException.createWithNullProductUuid();

        if (Objects.isNull(categoryUuid))
            throw ProductCategoryException.createWithNullCategoryUuid();

        Product product;
        try {
            product = productService.get(productUuid);
        } catch (AlphanahBaseException exception) {
            throw ProductCategoryException.createWithNullProductObject();
        }

        Category category;
        try {
            category = categoryService.get(categoryUuid);
        } catch (AlphanahBaseException exception) {
            throw ProductCategoryException.createWithNullCategoryObject();
        }

        if (repository.existsByProductAndCategory(product, category))
            throw ProductCategoryException.createDuplicateRelationship();

        if (!product.getCreatorUuid().equals(creatorUuid.toString()))
            throw ProductCategoryException.createNotOwned();

        ProductCategory entity = new ProductCategory();
        entity.setProduct(product);
        entity.setCategory(category);
        return repository.save(entity);
    }

    public void delete(UUID creatorUuid, UUID productUuid, UUID categoryUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ProductCategoryException.deleteWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ProductCategoryException.deleteWithNullProductUuid();

        if (Objects.isNull(categoryUuid))
            throw ProductCategoryException.deleteWithNullCategoryUuid();

        Product product;
        try {
            product = productService.get(productUuid);
        } catch (AlphanahBaseException exception) {
            throw ProductCategoryException.deleteWithNullProductObject();
        }

        Category category;
        try {
            category = categoryService.get(categoryUuid);
        } catch (AlphanahBaseException exception) {
            throw ProductCategoryException.deleteWithNullCategoryObject();
        }

        Optional<ProductCategory> optional = repository.findByProductAndCategory(product, category);
        if (optional.isEmpty())
            throw ProductCategoryException.deleteNullRelation();

        if (!product.getCreatorUuid().equals(creatorUuid.toString()))
            throw ProductCategoryException.deleteNotOwned();

        ProductCategory entity = optional.get();
        repository.delete(entity);
    }

}
