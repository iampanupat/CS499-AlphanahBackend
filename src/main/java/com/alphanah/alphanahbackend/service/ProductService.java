package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.ProductException;
import com.alphanah.alphanahbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    private final int PRODUCT_NAME_MAX_LENGTH = 120;
    private final int PRODUCT_DESCRIPTION_MAX_LENGTH = 255;

    public List<Product> getAll() {
        return (List<Product>) repository.findAll();
    }

    public Product get(UUID uuid) throws AlphanahBaseException {
        if (Objects.isNull(uuid))
            throw ProductException.getWithNullUuid();

        Optional<Product> optional = repository.findById(uuid.toString());
        if (optional.isEmpty())
            throw ProductException.getNullObject();

        return optional.get();
    }

    public Product create(UUID creatorUuid, String name, String description) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ProductException.createWithNullCreatorUuid();

        if (Objects.isNull(name))
            throw ProductException.createWithNullName();

        if (Objects.isNull(description))
            throw ProductException.createWithNullDescription();

        if (name.isEmpty())
            throw ProductException.createWithEmptyName();

        if (description.isEmpty())
            throw ProductException.createWithEmptyDescription();

        if (name.length() > PRODUCT_NAME_MAX_LENGTH)
            throw ProductException.createWithMaxLengthName();

        if (description.length() > PRODUCT_DESCRIPTION_MAX_LENGTH)
            throw ProductException.createWithMaxLengthDescription();

        Product entity = new Product();
        entity.setCreatorUuid(creatorUuid.toString());
        entity.setName(name);
        entity.setDescription(description);
        return repository.save(entity);
    }

    public Product update(UUID creatorUuid, UUID uuid, String name, String description) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ProductException.updateWithNullCreatorUuid();

        if (Objects.isNull(uuid))
            throw ProductException.updateWithNullUuid();

        if (Objects.isNull(name))
            throw ProductException.updateWithNullName();

        if (Objects.isNull(description))
            throw ProductException.updateWithNullDescription();

        if (name.isEmpty())
            throw ProductException.updateWithEmptyName();

        if (description.isEmpty())
            throw ProductException.updateWithEmptyDescription();

        if (name.length() > PRODUCT_NAME_MAX_LENGTH)
            throw ProductException.updateWithMaxLengthName();

        if (description.length() > PRODUCT_DESCRIPTION_MAX_LENGTH)
            throw ProductException.updateWithMaxLengthDescription();

        Product entity;
        try {
            entity = this.get(uuid);
        } catch (AlphanahBaseException exception) {
            throw ProductException.updateNullObject();
        }

        if (!entity.getCreatorUuid().equals(creatorUuid.toString()))
            throw ProductException.updateNotOwned();

        entity.setName(name);
        entity.setDescription(description);
        return repository.save(entity);
    }

    public void delete(UUID creatorUuid, UUID uuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ProductException.deleteWithNullCreatorUuid();

        if (Objects.isNull(uuid))
            throw ProductException.deleteWithNullUuid();

        Product entity;
        try {
            entity = this.get(uuid);
        } catch (AlphanahBaseException exception) {
            throw ProductException.deleteNullObject();
        }

        if (!entity.getCreatorUuid().equals(creatorUuid.toString()))
            throw ProductException.deleteNotOwned();

        repository.delete(entity);
    }

}
