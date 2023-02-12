package com.alphanah.alphanahbackend.exception;

public class ProductException extends AlphanahBaseException {

    public ProductException(String message) {
        super("product." + message);
    }

    public static ProductException searchWithNullKeyword() {
        return new ProductException("search.with.null.keyword");
    }

    public static ProductException searchWithEmptyKeyword() {
        return new ProductException("search.with.empty.keyword");
    }

    public static ProductException getWithNullUuid() {
        return new ProductException("get.with.null.uuid");
    }

    public static ProductException getNullObject() {
        return new ProductException("get.null.object");
    }

    public static ProductException createWithNullCreatorUuid() {
        return new ProductException("create.with.null.creator.uuid");
    }

    public static ProductException createWithNullName() {
        return new ProductException("create.with.null.name");
    }

    public static ProductException createWithNullDescription() {
        return new ProductException("create.with.null.description");
    }

    public static ProductException createWithEmptyName() {
        return new ProductException("create.with.empty.name");
    }

    public static ProductException createWithEmptyDescription() {
        return new ProductException("create.with.empty.description");
    }

    public static ProductException createWithMaxLengthName() {
        return new ProductException("create.with.max.length.name");
    }

    public static ProductException createWithMaxLengthDescription() {
        return new ProductException("create.with.max.length.description");
    }

    public static ProductException updateWithNullCreatorUuid() {
        return new ProductException("update.with.null.creator.uuid");
    }

    public static ProductException updateWithNullUuid() {
        return new ProductException("update.with.null.uuid");
    }

    public static ProductException updateWithNullName() {
        return new ProductException("update.with.null.name");
    }

    public static ProductException updateWithNullDescription() {
        return new ProductException("update.with.null.description");
    }

    public static ProductException updateWithEmptyName() {
        return new ProductException("update.with.empty.name");
    }

    public static ProductException updateWithEmptyDescription() {
        return new ProductException("update.with.empty.description");
    }

    public static ProductException updateWithMaxLengthName() {
        return new ProductException("update.with.max.length.name");
    }

    public static ProductException updateWithMaxLengthDescription() {
        return new ProductException("update.with.max.length.description");
    }

    public static ProductException updateNullObject() {
        return new ProductException("update.null.object");
    }

    public static ProductException updateNotOwned() {
        return new ProductException("update.not.owned");
    }

    public static ProductException deleteWithNullCreatorUuid() {
        return new ProductException("delete.with.null.creator.uuid");
    }

    public static ProductException deleteWithNullUuid() {
        return new ProductException("delete.with.null.uuid");
    }

    public static ProductException deleteNullObject() {
        return new ProductException("delete.null.object");
    }

    public static ProductException deleteNotOwned() {
        return new ProductException("delete.not.owned");
    }

}
