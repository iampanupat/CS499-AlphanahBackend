package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Image;
import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.ProductException;
import com.alphanah.alphanahbackend.repository.ProductRepository;
import com.alphanah.alphanahbackend.utility.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAllProducts() {
        List<Product> productList = (List<Product>) repository.findAll();
        List<Product> products = new ArrayList<>();

        for (Product product: productList)
            if (!product.isDeleted())
                products.add(product);

        return products;
    }

    public Product findProduct(UUID productUuid) throws AlphanahBaseException {
        if (Objects.isNull(productUuid))
            throw ProductException.cannotFindWithNullProductUuid();

        Optional<Product> optional = repository.findById(productUuid);
        if (optional.isEmpty())
            throw ProductException.notFound();

        if (optional.get().isDeleted())
            throw ProductException.notFound();

        return optional.get();
    }

    public Product createProduct(UUID creatorUuid, String name, String description) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ProductException.cannotCreateWithNullCreatorUuid();

        if (Objects.isNull(name))
            throw ProductException.cannotCreateWithNullName();

        if (Objects.isNull(description))
            throw ProductException.cannotCreateWithNullDescription();

        if (name.isEmpty())
            throw ProductException.cannotCreateWithEmptyName();

        if (description.isEmpty())
            throw ProductException.cannotCreateWithEmptyDescription();

        if (name.length() > Env.PRODUCT_NAME_MAX_LENGTH)
            throw ProductException.cannotCreateWithNameExceedMaxLength();

        if (description.length() > Env.PRODUCT_DESCRIPTION_MAX_LENGTH)
            throw ProductException.cannotCreateWithDescriptionExceedMaxLength();

        Product entity = new Product();
        entity.setCreatorUuid(creatorUuid);
        entity.setName(name);
        entity.setDescription(description);
        entity.setCreateDate(new Date());
        return repository.save(entity);
    }

    public Product updateProduct(UUID creatorUuid, UUID productUuid, String name, String description) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ProductException.cannotUpdateWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ProductException.cannotUpdateWithNullProductUuid();

        if (Objects.isNull(name))
            throw ProductException.cannotUpdateWithNullName();

        if (Objects.isNull(description))
            throw ProductException.cannotUpdateWithNullDescription();

        if (name.isEmpty())
            throw ProductException.cannotUpdateWithEmptyName();

        if (description.isEmpty())
            throw ProductException.cannotUpdateWithEmptyDescription();

        if (name.length() > Env.PRODUCT_NAME_MAX_LENGTH)
            throw ProductException.cannotUpdateWithNameExceedMaxLength();

        if (description.length() > Env.PRODUCT_DESCRIPTION_MAX_LENGTH)
            throw ProductException.cannotUpdateWithDescriptionExceedMaxLength();

        Product entity = this.findProduct(productUuid);

        if (!entity.getCreatorUuid().equals(creatorUuid))
            throw ProductException.cannotUpdateNotOwned();

        entity.setName(name);
        entity.setDescription(description);
        return repository.save(entity);
    }

    public void deleteProduct(UUID creatorUuid, UUID productUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ProductException.cannotDeleteWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ProductException.cannotDeleteWithNullProductUuid();

        Product entity = this.findProduct(productUuid);

        if (!entity.getCreatorUuid().equals(creatorUuid))
            throw ProductException.cannotDeleteNotOwned();

        entity.setSoftDelete(true);
        repository.save(entity);
    }

}
