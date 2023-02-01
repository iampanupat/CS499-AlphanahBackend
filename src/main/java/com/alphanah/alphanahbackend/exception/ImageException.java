package com.alphanah.alphanahbackend.exception;

public class ImageException extends AlphanahBaseException {

    public ImageException(String message) {
        super("image." + message);
    }

    public static ImageException getAllWithNullProductUuid() {
        return new ImageException("get.all.with.null.product.uuid");
    }

    public static ImageException getAllWithNullReviewUuid() {
        return new ImageException("get.all.with.null.review.uuid");
    }

    public static ImageException getNullProductObject() {
        return new ImageException("get.null.product.object");
    }

    public static ImageException getNullReviewObject() {
        return new ImageException("get.null.review.object");
    }

    public static ImageException getWithNullProductUuid() {
        return new ImageException("get.with.null.product.uuid");
    }

    public static ImageException getWithNullReviewUuid() {
        return new ImageException("get.with.null.review.uuid");
    }

    public static ImageException getWithNullUuid() {
        return new ImageException("get.with.null.uuid");
    }

    public static ImageException getWithNullProductObject() {
        return new ImageException("get.with.null.product.object");
    }

    public static ImageException getWithNullReviewObject() {
        return new ImageException("get.with.null.review.object");
    }

    public static ImageException getNullObject() {
        return new ImageException("get.null.object");
    }

    public static ImageException getWithInvalidProductUuid() {
        return new ImageException("get.with.invalid.product.uuid");
    }

    public static ImageException getWithInvalidReviewUuid() {
        return new ImageException("get.with.invalid.review.uuid");
    }

    public static ImageException createWithNullCreatorUuid() {
        return new ImageException("create.with.null.creator.uuid");
    }

    public static ImageException createWithNullProductUuid() {
        return new ImageException("create.with.null.product.uuid");
    }

    public static ImageException createWithNullReviewUuid() {
        return new ImageException("create.with.null.review.uuid");
    }

    public static ImageException createWithNullPath() {
        return new ImageException("create.with.null.path");
    }

    public static ImageException createWithEmptyPath() {
        return new ImageException("create.with.empty.path");
    }

    public static ImageException createWithMaxLengthPath() {
        return new ImageException("create.with.max.length.path");
    }

    public static ImageException createWithNullProductObject() {
        return new ImageException("create.with.null.product.object");
    }

    public static ImageException createWithNullReviewObject() {
        return new ImageException("create.with.null.review.object");
    }

    public static ImageException createNotOwned() {
        return new ImageException("create.not.owned");
    }

    public static ImageException deleteWithNullCreatorUuid() {
        return new ImageException("delete.with.null.creator.uuid");
    }

    public static ImageException deleteWithNullProductUuid() {
        return new ImageException("delete.with.null.product.uuid");
    }

    public static ImageException deleteWithNullReviewUuid() {
        return new ImageException("delete.with.null.review.uuid");
    }

    public static ImageException deleteWithNullUuid() {
        return new ImageException("delete.with.null.uuid");
    }

    public static ImageException deleteWithNullProductObject() {
        return new ImageException("delete.with.null.product.object");
    }

    public static ImageException deleteNullObject() {
        return new ImageException("delete.null.object");
    }

    public static ImageException deleteNotOwned() {
        return new ImageException("delete.not.owned");
    }

}
