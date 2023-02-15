package com.alphanah.alphanahbackend.exception;

import org.springframework.security.core.parameters.P;

public class ProductOptionException extends AlphanahBaseException {

    public ProductOptionException(String message) {
        super("product.option." + message);
    }

    public static ProductOptionException cannotFindWithNullProductUuid() {
        return new ProductOptionException("cannot.find.with.null.product.uuid");
    }

    public static ProductOptionException cannotFindWithNullProductOptionUuid() {
        return new ProductOptionException("cannot.find.with.null.product.option.uuid");
    }

    public static ProductOptionException notFound() {
        return new ProductOptionException("not.found");
    }

    public static ProductOptionException cannotCreateWithNullCreatorUuid() {
        return new ProductOptionException("cannot.create.with.null.creator.uuid");
    }

    public static ProductOptionException cannotCreateWithNullProductUuid() {
        return new ProductOptionException("cannot.create.with.null.product.uuid");
    }

    public static ProductOptionException cannotCreateWithNullName() {
        return new ProductOptionException("cannot.create.with.null.name");
    }

    public static ProductOptionException cannotCreateWithNullPrice() {
        return new ProductOptionException("cannot.create.with.null.price");
    }

    public static ProductOptionException cannotCreateWithNullQuantity() {
        return new ProductOptionException("cannot.create.with.null.quantity");
    }

    public static ProductOptionException cannotCreateWithEmptyName() {
        return new ProductOptionException("cannot.create.with.empty.name");
    }

    public static ProductOptionException cannotCreateWithNegativeOrZeroPrice() {
        return new ProductOptionException("cannot.create.with.negative.or.zero.price");
    }

    public static ProductOptionException cannotCreateWithNegativeOrZeroQuantity() {
        return new ProductOptionException("cannot.create.with.negative.or.zero.quantity");
    }

    public static ProductOptionException cannotCreateWithNameExceedMaxLength() {
        return new ProductOptionException("cannot.create.with.name.exceed.max.length");
    }

    public static ProductOptionException cannotCreateWithPriceExceedMaxValue() {
        return new ProductOptionException("cannot.create.with.price.exceed.max.value");
    }

    public static ProductOptionException cannotCreateWithQuantityExceedMaxValue() {
        return new ProductOptionException("cannot.create.with.quantity.exceed.max.value");
    }

    public static ProductOptionException cannotCreateNotOwned() {
        return new ProductOptionException("cannot.create.not.owned");
    }

    public static ProductOptionException cannotUpdateWithNullCreatorUuid() {
        return new ProductOptionException("cannot.update.with.null.creator.uuid");
    }

    public static ProductOptionException cannotUpdateWithNullProductUuid() {
        return new ProductOptionException("cannot.update.with.null.product.uuid");
    }

    public static ProductOptionException cannotUpdateWithNullProductOptionUuid() {
        return new ProductOptionException("cannot.update.with.null.product.option.uuid");
    }

    public static ProductOptionException cannotUpdateWithNullName() {
        return new ProductOptionException("cannot.update.with.null.name");
    }

    public static ProductOptionException cannotUpdateWithNullPrice() {
        return new ProductOptionException("cannot.update.with.null.price");
    }

    public static ProductOptionException cannotUpdateWithNullQuantity() {
        return new ProductOptionException("cannot.update.with.null.quantity");
    }

    public static ProductOptionException cannotUpdateWithEmptyName() {
        return new ProductOptionException("cannot.update.with.empty.name");
    }

    public static ProductOptionException cannotUpdateWithNegativeOrZeroPrice() {
        return new ProductOptionException("cannot.update.with.negative.or.zero.price");
    }

    public static ProductOptionException cannotUpdateWithNegativeOrZeroQuantity() {
        return new ProductOptionException("cannot.update.with.negative.or.zero.quantity");
    }

    public static ProductOptionException cannotUpdateWithNameExceedMaxLength() {
        return new ProductOptionException("cannot.update.with.name.exceed.max.length");
    }

    public static ProductOptionException cannotUpdateWithPriceExceedMaxValue() {
        return new ProductOptionException("cannot.update.with.price.exceed.max.value");
    }

    public static ProductOptionException cannotUpdateWithQuantityExceedMaxValue() {
        return new ProductOptionException("cannot.update.with.quantity.exceed.max.value");
    }

    public static ProductOptionException cannotUpdateNotOwned() {
        return new ProductOptionException("cannot.update.not.owned");
    }

    public static ProductOptionException cannotDeleteWithNullCreatorUuid() {
        return new ProductOptionException("cannot.delete.with.null.creator.uuid");
    }

    public static ProductOptionException cannotDeleteWithNullProductUuid() {
        return new ProductOptionException("cannot.delete.with.null.product.uuid");
    }

    public static ProductOptionException cannotDeleteWithNullProductOptionUuid() {
        return new ProductOptionException("cannot.delete.with.null.product.option.uuid");
    }

    public static ProductOptionException cannotDeleteNotOwned() {
        return new ProductOptionException("cannot.delete.not.owned");
    }

}
