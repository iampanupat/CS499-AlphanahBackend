package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Account;
import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.entity.ProductOption;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.ProductOptionException;
import com.alphanah.alphanahbackend.repository.ProductOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductOptionService {

    @Autowired
    private ProductOptionRepository repository;

    @Autowired
    private ProductService productService;

    private final int PRODUCT_OPTION_NAME_MAX_LENGTH = 120;
    private final double PRODUCT_OPTION_PRICE_MAX_VALUE = 1000000;
    private final int PRODUCT_OPTION_QUANTITY_MAX_VALUE = 1000000;

    public List<ProductOption> getAll(UUID productUuid) throws AlphanahBaseException {
        if (Objects.isNull(productUuid))
            throw ProductOptionException.getAllWithNullProductUuid();

        Product product;
        try {
            product = productService.get(productUuid);
        } catch (AlphanahBaseException exception) {
            throw ProductOptionException.getNullProductObject();
        }

        return repository.findAllByProduct(product);
    }

    public ProductOption get(UUID productUuid, UUID uuid) throws AlphanahBaseException {
        if (Objects.isNull(productUuid))
            throw ProductOptionException.getWithNullProductUuid();

        if (Objects.isNull(uuid))
            throw ProductOptionException.getWithNullUuid();

        Product product;
        try {
            product = productService.get(productUuid);
        } catch (AlphanahBaseException exception) {
            throw ProductOptionException.getWithNullProductObject();
        }

        Optional<ProductOption> optional = repository.findById(uuid.toString());
        if (optional.isEmpty())
            throw ProductOptionException.getNullObject();

        if (!optional.get().getProduct().getUuid().equals(product.getUuid()))
            throw ProductOptionException.getWithInvalidRootProductUuid();

        return optional.get();
    }

    public ProductOption create(UUID creatorUuid, UUID productUuid, String name, double price, int quantity) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ProductOptionException.createWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ProductOptionException.createWithNullProductUuid();

        if (Objects.isNull(name))
            throw ProductOptionException.createWithNullName();

        if (name.isEmpty())
            throw ProductOptionException.createWithEmptyName();

        if (price < 0)
            throw ProductOptionException.createWithNegativePrice();

        if (quantity < 0)
            throw ProductOptionException.createWithNegativeQuantity();

        if (name.length() > PRODUCT_OPTION_NAME_MAX_LENGTH)
            throw ProductOptionException.createWithMaxLengthName();

        if (price > PRODUCT_OPTION_PRICE_MAX_VALUE)
            throw ProductOptionException.createWithMaxValuePrice();

        if (quantity > PRODUCT_OPTION_QUANTITY_MAX_VALUE)
            throw ProductOptionException.createWithMaxValueQuantity();

        Product product;
        try {
            product = productService.get(productUuid);
        } catch (AlphanahBaseException exception) {
            throw ProductOptionException.createWithNullProductObject();
        }

        if (!product.getCreatorUuid().equals(creatorUuid.toString()))
            throw ProductOptionException.createNotOwned();

        ProductOption entity = new ProductOption();
        entity.setProduct(product);
        entity.setName(name);
        entity.setPrice(price);
        entity.setQuantity(quantity);
        return repository.save(entity);
    }

    public ProductOption update(UUID creatorUuid, UUID productUuid, UUID uuid, String name, double price, int quantity) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ProductOptionException.updateWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ProductOptionException.updateWithNullProductUuid();

        if (Objects.isNull(uuid))
            throw ProductOptionException.updateWithNullUuid();

        if (Objects.isNull(name))
            throw ProductOptionException.updateWithNullName();

        if (name.isEmpty())
            throw ProductOptionException.updateWithEmptyName();

        if (price < 0)
            throw ProductOptionException.updateWithNegativePrice();

        if (quantity < 0)
            throw ProductOptionException.updateWithNegativeQuantity();

        if (name.length() > PRODUCT_OPTION_NAME_MAX_LENGTH)
            throw ProductOptionException.updateWithMaxLengthName();

        if (price > PRODUCT_OPTION_PRICE_MAX_VALUE)
            throw ProductOptionException.updateWithMaxValuePrice();

        if (quantity > PRODUCT_OPTION_QUANTITY_MAX_VALUE)
            throw ProductOptionException.updateWithMaxValueQuantity();

        Product product;
        try {
            product = productService.get(productUuid);
        } catch (AlphanahBaseException exception) {
            throw ProductOptionException.updateWithNullProductObject();
        }

        ProductOption entity;
        try {
            entity = this.get(productUuid, uuid);
        } catch (AlphanahBaseException exception) {
            throw ProductOptionException.updateNullObject();
        }

        if (!product.getCreatorUuid().equals(creatorUuid.toString()))
            throw ProductOptionException.updateNotOwned();

        entity.setName(name);
        entity.setPrice(price);
        entity.setQuantity(quantity);
        return repository.save(entity);
    }

    public void delete(UUID creatorUUID, UUID productUuid, UUID uuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUUID))
            throw ProductOptionException.deleteWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ProductOptionException.deleteWithNullProductUuid();

        if (Objects.isNull(uuid))
            throw ProductOptionException.deleteWithNullUuid();

        Product product;
        try {
            product = productService.get(productUuid);
        } catch (AlphanahBaseException exception) {
            throw ProductOptionException.deleteWithNullProductObject();
        }

        ProductOption entity;
        try {
            entity = this.get(productUuid, uuid);
        } catch (AlphanahBaseException exception) {
            throw ProductOptionException.deleteNullObject();
        }

        if (!product.getCreatorUuid().equals(creatorUUID.toString()))
            throw ProductOptionException.deleteNotOwned();

        repository.delete(entity);
    }

}
