package com.alphanah.alphanahbackend.exception;

import org.springframework.security.core.parameters.P;

public class ProductOptionException extends AlphanahBaseException {

    public ProductOptionException(String message) {
        super("product.option." + message);
    }

    public static ProductOptionException getAllWithNullProductUuid() {
        return new ProductOptionException("get.all.with.null.product.uuid");
    }

    public static ProductOptionException getNullProductObject() {
        return new ProductOptionException("get.null.product.object");
    }

    public static ProductOptionException getWithNullProductUuid() {
        return new ProductOptionException("get.with.null.product.uuid");
    }

    public static ProductOptionException getWithNullUuid() {
        return new ProductOptionException("get.with.null.uuid");
    }

    public static ProductOptionException getWithNullProductObject() {
        return new ProductOptionException("get.with.null.product.object");
    }

    public static ProductOptionException getNullObject() {
        return new ProductOptionException("get.null.object");
    }

    public static ProductOptionException getWithInvalidRootProductUuid() {
        return new ProductOptionException("get.with.invalid.root.product.uuid");
    }

    public static ProductOptionException createWithNullCreatorUuid() {
        return new ProductOptionException("create.with.null.creator.uuid");
    }

    public static ProductOptionException createWithNullProductUuid() {
        return new ProductOptionException("create.with.null.product.uuid");
    }

    public static ProductOptionException createWithNullName() {
        return new ProductOptionException("create.with.null.name");
    }

    public static ProductOptionException createWithEmptyName() {
        return new ProductOptionException("create.with.empty.name");
    }

    public static ProductOptionException createWithNegativePrice() {
        return new ProductOptionException("create.with.negative.price");
    }

    public static ProductOptionException createWithNegativeQuantity() {
        return new ProductOptionException("create.with.negative.quantity");
    }

    public static ProductOptionException createWithMaxLengthName() {
        return new ProductOptionException("create.with.max.length.name");
    }

    public static ProductOptionException createWithMaxValuePrice() {
        return new ProductOptionException("create.with.max.value.price");
    }

    public static ProductOptionException createWithMaxValueQuantity() {
        return new ProductOptionException("create.with.max.value.quantity");
    }

    public static ProductOptionException createWithNullProductObject() {
        return new ProductOptionException("create.with.null.product.object");
    }

    public static ProductOptionException createNotOwned() {
        return new ProductOptionException("create.not.owned");
    }

    public static ProductOptionException updateWithNullCreatorUuid() {
        return new ProductOptionException("update.with.null.creator.uuid");
    }

    public static ProductOptionException updateWithNullProductUuid() {
        return new ProductOptionException("update.with.null.product.uuid");
    }

    public static ProductOptionException updateWithNullUuid() {
        return new ProductOptionException("update.with.null.uuid");
    }

    public static ProductOptionException updateWithNullName() {
        return new ProductOptionException("update.with.null.name");
    }

    public static ProductOptionException updateWithEmptyName() {
        return new ProductOptionException("update.with.empty.name");
    }

    public static ProductOptionException updateWithNegativePrice() {
        return new ProductOptionException("update.with.negative.price");
    }

    public static ProductOptionException updateWithNegativeQuantity() {
        return new ProductOptionException("update.with.negative.quantity");
    }

    public static ProductOptionException updateWithMaxLengthName() {
        return new ProductOptionException("update.with.max.length.name");
    }

    public static ProductOptionException updateWithMaxValuePrice() {
        return new ProductOptionException("update.with.max.value.price");
    }

    public static ProductOptionException updateWithMaxValueQuantity() {
        return new ProductOptionException("update.with.max.value.quantity");
    }

    public static ProductOptionException updateWithNullProductObject() {
        return new ProductOptionException("update.with.null.product.object");
    }

    public static ProductOptionException updateNullObject() {
        return new ProductOptionException("update.null.object");
    }

    public static ProductOptionException updateNotOwned() {
        return new ProductOptionException("update.not.owned");
    }

    public static ProductOptionException deleteWithNullCreatorUuid() {
        return new ProductOptionException("delete.with.null.creator.uuid");
    }

    public static ProductOptionException deleteWithNullProductUuid() {
        return new ProductOptionException("delete.with.null.product.uuid");
    }

    public static ProductOptionException deleteWithNullUuid() {
        return new ProductOptionException("delete.with.null.uuid");
    }

    public static ProductOptionException deleteWithNullProductObject() {
        return new ProductOptionException("delete.with.null.product.object");
    }

    public static ProductOptionException deleteNullObject() {
        return new ProductOptionException("delete.null.object");
    }

    public static ProductOptionException deleteNotOwned() {
        return new ProductOptionException("delete.not.owned");
    }

}
