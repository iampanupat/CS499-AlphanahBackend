package com.alphanah.alphanahbackend.exception;

public class ReviewException extends AlphanahBaseException {

    public ReviewException(String message) {
        super("review." + message);
    }

    public static ReviewException getAllWithNullProductUuid() {
        return new ReviewException("get.all.with.null.product.uuid");
    }

    public static ReviewException getNullProductObject() {
        return new ReviewException("get.null.product.object");
    }

    public static ReviewException getWithNullProductUuid() {
        return new ReviewException("get.with.null.product.uuid");
    }

    public static ReviewException getWithNullUuid() {
        return new ReviewException("get.with.null.uuid");
    }

    public static ReviewException getWithNullProductObject() {
        return new ReviewException("get.with.null.product.object");
    }

    public static ReviewException getNullObject() {
        return new ReviewException("get.null.object");
    }

    public static ReviewException getWithInvalidProductUuid() {
        return new ReviewException("get.with.invalid.product.uuid");
    }

    public static ReviewException createWithNullCreatorUuid() {
        return new ReviewException("create.with.null.creator.uuid");
    }

    public static ReviewException createWithNullProductUuid() {
        return new ReviewException("create.with.null.product.uuid");
    }

    public static ReviewException createWithNullHeader() {
        return new ReviewException("create.with.null.header");
    }

    public static ReviewException createWithNullMessage() {
        return new ReviewException("create.with.null.message");
    }

    public static ReviewException createWithEmptyHeader() {
        return new ReviewException("create.with.empty.header");
    }

    public static ReviewException createWithEmptyMessage() {
        return new ReviewException("create.with.empty.message");
    }

    public static ReviewException createWithNegativeRating() {
        return new ReviewException("create.with.negative.rating");
    }

    public static ReviewException createWithMaxLengthHeader() {
        return new ReviewException("create.with.max.length.header");
    }

    public static ReviewException createWithMaxLengthMessage() {
        return new ReviewException("create.with.max.length.message");
    }

    public static ReviewException createWithMaxValueRating() {
        return new ReviewException("create.with.max.value.rating");
    }

    public static ReviewException createWithNullProductObject() {
        return new ReviewException("create.with.null.product.object");
    }

    public static ReviewException updateWithNullCreatorUuid() {
        return new ReviewException("update.with.null.creator.uuid");
    }

    public static ReviewException updateWithNullProductUuid() {
        return new ReviewException("update.with.null.product.uuid");
    }

    public static ReviewException updateWithNullUuid() {
        return new ReviewException("update.with.null.uuid");
    }

    public static ReviewException updateWithNullHeader() {
        return new ReviewException("update.with.null.header");
    }

    public static ReviewException updateWithNullMessage() {
        return new ReviewException("update.with.null.message");
    }

    public static ReviewException updateWithEmptyHeader() {
        return new ReviewException("update.with.empty.header");
    }

    public static ReviewException updateWithEmptyMessage() {
        return new ReviewException("update.with.empty.message");
    }

    public static ReviewException updateWithNegativeRating() {
        return new ReviewException("update.with.negative.rating");
    }

    public static ReviewException updateWithMaxLengthHeader() {
        return new ReviewException("update.with.max.length.header");
    }

    public static ReviewException updateWithMaxLengthMessage() {
        return new ReviewException("update.with.max.length.message");
    }

    public static ReviewException updateWithMaxValueRating() {
        return new ReviewException("update.with.max.value.rating");
    }

    public static ReviewException updateNullObject() {
        return new ReviewException("update.null.object");
    }

    public static ReviewException updateNotOwned() {
        return new ReviewException("update.not.owned");
    }

    public static ReviewException deleteWithNullCreatorUuid() {
        return new ReviewException("delete.with.null.creator.uuid");
    }

    public static ReviewException deleteWithNullProductUuid() {
        return new ReviewException("delete.with.null.product.uuid");
    }

    public static ReviewException deleteWithNullUuid() {
        return new ReviewException("delete.with.null.uuid");
    }

    public static ReviewException deleteNullObject() {
        return new ReviewException("delete.null.object");
    }

    public static ReviewException deleteNotOwned() {
        return new ReviewException("delete.not.owned");
    }

}
