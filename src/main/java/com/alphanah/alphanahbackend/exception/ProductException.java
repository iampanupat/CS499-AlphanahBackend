package com.alphanah.alphanahbackend.exception;

public class ProductException extends AlphanahBaseException {

    public ProductException(String message) {
        super("product." + message);
    }

    public static ProductException cannotFindWithNullProductUuid() {
        return new ProductException("cannot.find.with.null.product.uuid");
    }

    public static ProductException notFound() {
        return new ProductException("not.found");
    }

    public static ProductException cannotCreateWithNullCreatorUuid() {
        return new ProductException("cannot.create.with.null.creator.uuid");
    }

    public static ProductException cannotCreateWithNullName() {
        return new ProductException("cannot.create.with.null.name");
    }

    public static ProductException cannotCreateWithNullDescription() {
        return new ProductException("cannot.create.with.null.description");
    }

    public static ProductException cannotCreateWithEmptyName() {
        return new ProductException("cannot.create.with.empty.name");
    }

    public static ProductException cannotCreateWithEmptyDescription() {
        return new ProductException("cannot.create.with.empty.description");
    }

    public static ProductException cannotCreateWithNameExceedMaxLength() {
        return new ProductException("cannot.create.with.name.exceed.max.length");
    }

    public static ProductException cannotCreateWithDescriptionExceedMaxLength() {
        return new ProductException("cannot.create.with.description.exceed.max.length");
    }

    public static ProductException cannotUpdateWithNullCreatorUuid() {
        return new ProductException("cannot.update.with.null.creator.uuid");
    }

    public static ProductException cannotUpdateWithNullProductUuid() {
        return new ProductException("cannot.update.with.null.product.uuid");
    }

    public static ProductException cannotUpdateWithNullName() {
        return new ProductException("cannot.update.with.null.name");
    }

    public static ProductException cannotUpdateWithNullDescription() {
        return new ProductException("cannot.update.with.null.description");
    }

    public static ProductException cannotUpdateWithEmptyName() {
        return new ProductException("cannot.update.with.empty.name");
    }

    public static ProductException cannotUpdateWithEmptyDescription() {
        return new ProductException("cannot.update.with.empty.description");
    }

    public static ProductException cannotUpdateWithNameExceedMaxLength() {
        return new ProductException("cannot.update.with.name.exceed.max.length");
    }

    public static ProductException cannotUpdateWithDescriptionExceedMaxLength() {
        return new ProductException("cannot.update.with.description.exceed.max.length");
    }

    public static ProductException cannotUpdateNotOwned() {
        return new ProductException("cannot.update.not.owned");
    }

    public static ProductException cannotDeleteWithNullCreatorUuid() {
        return new ProductException("cannot.delete.with.null.creator.uuid");
    }

    public static ProductException cannotDeleteWithNullProductUuid() {
        return new ProductException("cannot.delete.with.null.product.uuid");
    }

    public static ProductException cannotDeleteNotOwned() {
        return new ProductException("cannot.delete.not.owned");
    }

}
