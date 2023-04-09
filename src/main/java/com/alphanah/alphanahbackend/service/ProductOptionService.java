package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Account;
import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.entity.ProductOption;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.ProductOptionException;
import com.alphanah.alphanahbackend.repository.ProductOptionRepository;
import com.alphanah.alphanahbackend.utility.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductOptionService {

    @Autowired
    private ProductOptionRepository repository;

    @Autowired
    private ProductService productService;

    public List<ProductOption> findAllProductOptions(UUID productUuid) throws AlphanahBaseException {
        if (Objects.isNull(productUuid))
            throw ProductOptionException.cannotFindWithNullProductUuid();

        Product product = productService.findProduct(productUuid);

        List<ProductOption> productOptionList = repository.findAllByProduct(product);
        List<ProductOption> productOptions = new ArrayList<>();
        for (ProductOption productOption: productOptionList)
            if (!productOption.isDeleted())
                productOptions.add(productOption);

        return productOptions;
    }

    public ProductOption findProductOption(UUID productUuid, UUID productOptionUuid) throws AlphanahBaseException {
        if (Objects.isNull(productUuid))
            throw ProductOptionException.cannotFindWithNullProductUuid();

        if (Objects.isNull(productOptionUuid))
            throw ProductOptionException.cannotFindWithNullProductOptionUuid();

        Product product = productService.findProduct(productUuid);
        Optional<ProductOption> optional = repository.findById(productOptionUuid);
        if (optional.isEmpty())
            throw ProductOptionException.notFound();

        ProductOption option = optional.get();
        if (!option.getProduct().getUuid().equals(product.getUuid()))
            throw ProductOptionException.notFound();

        if (option.isDeleted())
            throw ProductOptionException.notFound();

        return option;
    }

    public ProductOption createProductOption(UUID creatorUuid, UUID productUuid, String name, Double price, Integer quantity) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ProductOptionException.cannotCreateWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ProductOptionException.cannotCreateWithNullProductUuid();

        if (Objects.isNull(name))
            throw ProductOptionException.cannotCreateWithNullName();

        if (Objects.isNull(price))
            throw ProductOptionException.cannotCreateWithNullPrice();

        if (Objects.isNull(quantity))
            throw ProductOptionException.cannotCreateWithNullQuantity();

        if (name.isEmpty())
            throw ProductOptionException.cannotCreateWithEmptyName();

        if (price <= 0)
            throw ProductOptionException.cannotCreateWithNegativeOrZeroPrice();

        if (quantity <= 0)
            throw ProductOptionException.cannotCreateWithNegativeOrZeroQuantity();

        if (name.length() > Env.PRODUCT_NAME_MAX_LENGTH)
            throw ProductOptionException.cannotCreateWithNameExceedMaxLength();

        if (price > Env.PRODUCT_PRICE_MAX_VALUE)
            throw ProductOptionException.cannotCreateWithPriceExceedMaxValue();

        if (quantity > Env.PRODUCT_QUANTITY_MAX_VALUE)
            throw ProductOptionException.cannotCreateWithQuantityExceedMaxValue();

        Product product = productService.findProduct(productUuid);
        if (!product.getCreatorUuid().equals(creatorUuid))
            throw ProductOptionException.cannotCreateNotOwned();

        ProductOption entity = new ProductOption();
        entity.setProduct(product);
        entity.setName(name);
        entity.setPrice(price);
        entity.setQuantity(quantity);
        return repository.save(entity);
    }

    public ProductOption updateProductOption(UUID creatorUuid, UUID productUuid, UUID productOptionUuid, String name, Double price, Integer quantity) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ProductOptionException.cannotUpdateWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ProductOptionException.cannotUpdateWithNullProductUuid();

        if (Objects.isNull(productOptionUuid))
            throw ProductOptionException.cannotUpdateWithNullProductOptionUuid();

        if (Objects.isNull(name))
            throw ProductOptionException.cannotUpdateWithNullName();

        if (Objects.isNull(price))
            throw ProductOptionException.cannotUpdateWithNullPrice();

        if (Objects.isNull(quantity))
            throw ProductOptionException.cannotUpdateWithNullQuantity();

        if (name.isEmpty())
            throw ProductOptionException.cannotUpdateWithEmptyName();

        if (price <= 0)
            throw ProductOptionException.cannotUpdateWithNegativeOrZeroPrice();

        if (quantity <= 0)
            throw ProductOptionException.cannotUpdateWithNegativeOrZeroQuantity();

        if (name.length() > Env.PRODUCT_NAME_MAX_LENGTH)
            throw ProductOptionException.cannotUpdateWithNameExceedMaxLength();

        if (price > Env.PRODUCT_PRICE_MAX_VALUE)
            throw ProductOptionException.cannotUpdateWithPriceExceedMaxValue();

        if (quantity > Env.PRODUCT_QUANTITY_MAX_VALUE)
            throw ProductOptionException.cannotUpdateWithQuantityExceedMaxValue();

        Product product = productService.findProduct(productUuid);
        ProductOption entity = this.findProductOption(productUuid, productOptionUuid);
        if (!product.getCreatorUuid().equals(creatorUuid))
            throw ProductOptionException.cannotUpdateNotOwned();

        entity.setName(name);
        entity.setPrice(price);
        entity.setQuantity(quantity);
        return repository.save(entity);
    }

    public void deleteProductOption(UUID creatorUUID, UUID productUuid, UUID productOptionUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUUID))
            throw ProductOptionException.cannotDeleteWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ProductOptionException.cannotDeleteWithNullProductUuid();

        if (Objects.isNull(productOptionUuid))
            throw ProductOptionException.cannotDeleteWithNullProductOptionUuid();

        Product product = productService.findProduct(productUuid);
        ProductOption entity = this.findProductOption(productUuid, productOptionUuid);
        if (!product.getCreatorUuid().equals(creatorUUID))
            throw ProductOptionException.cannotDeleteNotOwned();

        entity.setSoftDelete(true);
        repository.save(entity);
    }

}
