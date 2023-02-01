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

    public Category create(UUID creatorUuid, String name) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw CategoryException.createWithNullCreatorUuid();

        if (Objects.isNull(name))
            throw CategoryException.createWithNullName();

        if (name.isEmpty())
            throw CategoryException.createWithEmptyName();

        if (name.length() > CATEGORY_NAME_MAX_LENGTH)
            throw CategoryException.createWithMaxLengthName();

        name = name.toLowerCase();
        if (repository.existsByName(name))
            throw CategoryException.createDuplicateName();

        Category entity = new Category();
        entity.setCreatorUuid(creatorUuid.toString());
        entity.setName(name);
        return repository.save(entity);
    }

    public Category update(UUID creatorUuid, UUID uuid, String name) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw CategoryException.updateWithNullCreatorUuid();

        if (Objects.isNull(uuid))
            throw CategoryException.updateWithNullUuid();

        if (Objects.isNull(name))
            throw CategoryException.updateWithNullName();

        if (name.isEmpty())
            throw CategoryException.updateWithEmptyName();

        if (name.length() > CATEGORY_NAME_MAX_LENGTH)
            throw CategoryException.updateWithMaxLengthName();

        name = name.toLowerCase();
        if (repository.existsByName(name))
            throw CategoryException.updateDuplicateName();

        Category entity;
        try {
            entity = this.get(uuid);
        } catch (AlphanahBaseException exception) {
            throw CategoryException.updateNullObject();
        }

        if (!entity.getCreatorUuid().equals(creatorUuid.toString()))
            throw CategoryException.updateNotOwned();
        entity.setName(name);

        return repository.save(entity);
    }

    public void delete(UUID creatorUuid, UUID uuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw CategoryException.deleteWithNullCreatorUuid();

        if (Objects.isNull(uuid))
            throw CategoryException.deleteWithNullUuid();

        Category entity;
        try {
            entity = this.get(uuid);
        } catch (AlphanahBaseException exception) {
            throw CategoryException.deleteNullObject();
        }

        if (!entity.getCreatorUuid().equals(creatorUuid.toString()))
            throw CategoryException.deleteNotOwned();

        repository.delete(entity);
    }

}
