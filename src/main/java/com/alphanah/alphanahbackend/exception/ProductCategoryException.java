package com.alphanah.alphanahbackend.exception;

import org.springframework.security.core.parameters.P;

public class ProductCategoryException extends AlphanahBaseException {

    public ProductCategoryException(String message) {
        super("product.category." + message);
    }

    public static ProductCategoryException cannotCreateWithNullCreatorUuid() {
        return new ProductCategoryException("cannot.create.with.null.creator.uuid");
    }

    public static ProductCategoryException cannotCreateWithNullProductUuid() {
        return new ProductCategoryException("cannot.create.with.null.product.uuid");
    }

    public static ProductCategoryException cannotCreateWithNullCategoryUuid() {
        return new ProductCategoryException("cannot.create.with.null.category.uuid");
    }

    public static ProductCategoryException cannotCreateDuplicateRelationship() {
        return new ProductCategoryException("cannot.create.duplicate.relationship");
    }

    public static ProductCategoryException cannotCreateNotOwned() {
        return new ProductCategoryException("cannot.create.not.owned");
    }

    public static ProductCategoryException cannotDeleteWithNullCreatorUuid() {
        return new ProductCategoryException("cannot.delete.with.null.creator.uuid");
    }

    public static ProductCategoryException cannotDeleteWithNullProductUuid() {
        return new ProductCategoryException("cannot.delete.with.null.product.uuid");
    }

    public static ProductCategoryException cannotDeleteWithNullCategoryUuid() {
        return new ProductCategoryException("cannot.delete.with.null.category.uuid");
    }

    public static ProductCategoryException cannotDeleteNotOwned() {
        return new ProductCategoryException("cannot.delete.not.owned");
    }

    public static ProductCategoryException cannotDeleteNullRelationship() {
        return new ProductCategoryException("cannot.delete.null.relationship");
    }

}
