package com.alphanah.alphanahbackend.exception;

public class ProductCategoryException extends AlphanahBaseException {

    public ProductCategoryException(String message) {
        super("product.category." + message);
    }

    public static ProductCategoryException createWithNullCreatorUuid() {
        return new ProductCategoryException("create.with.null.creator.uuid");
    }

    public static ProductCategoryException createWithNullProductUuid() {
        return new ProductCategoryException("create.with.null.product.uuid");
    }

    public static ProductCategoryException createWithNullCategoryUuid() {
        return new ProductCategoryException("create.with.null.category.uuid");
    }

    public static ProductCategoryException createWithNullProductObject() {
        return new ProductCategoryException("create.with.null.product.object");
    }

    public static ProductCategoryException createWithNullCategoryObject() {
        return new ProductCategoryException("create.with.null.category.object");
    }

    public static ProductCategoryException createDuplicateRelationship() {
        return new ProductCategoryException("create.duplicate.relationship");
    }

    public static ProductCategoryException createNotOwned() {
        return new ProductCategoryException("create.not.owned");
    }

    public static ProductCategoryException deleteWithNullCreatorUuid() {
        return new ProductCategoryException("delete.with.null.creator.uuid");
    }

    public static ProductCategoryException deleteWithNullProductUuid() {
        return new ProductCategoryException("delete.with.null.product.uuid");
    }

    public static ProductCategoryException deleteWithNullCategoryUuid() {
        return new ProductCategoryException("delete.with.null.category.uuid");
    }

    public static ProductCategoryException deleteWithNullProductObject() {
        return new ProductCategoryException("delete.with.null.product.object");
    }

    public static ProductCategoryException deleteWithNullCategoryObject() {
        return new ProductCategoryException("delete.with.null.category.object");
    }

    public static ProductCategoryException deleteNullRelation() {
        return new ProductCategoryException("delete.null.relation");
    }

    public static ProductCategoryException deleteNotOwned() {
        return new ProductCategoryException("delete.not.owned");
    }

}
