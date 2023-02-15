package com.alphanah.alphanahbackend.exception;

public class ImageException extends AlphanahBaseException {

    public ImageException(String message) {
        super("image." + message);
    }

    public static ImageException cannotFindWithNullProductUuid() {
        return new ImageException("cannot.find.with.null.product.uuid");
    }

    public static ImageException cannotFindWithNullReviewUuid() {
        return new ImageException("cannot.find.with.null.review.uuid");
    }

    public static ImageException cannotFindWithNullImageUuid() {
        return new ImageException("cannot.find.with.null.image.uuid");
    }

    public static ImageException notFound() {
        return new ImageException("not.found");
    }

    public static ImageException cannotFindWithInvalidProductUuid() {
        return new ImageException("cannot.find.with.invalid.product.uuid");
    }

    public static ImageException cannotFindWithInvalidReviewUuid() {
        return new ImageException("cannot.find.with.invalid.review.uuid");
    }

    public static ImageException cannotCreateWithNullCreatorUuid() {
        return new ImageException("cannot.create.with.null.creator.uuid");
    }

    public static ImageException cannotCreateWithNullProductUuid() {
        return new ImageException("cannot.create.with.null.product.uuid");
    }

    public static ImageException cannotCreateWithNullReviewUuid() {
        return new ImageException("cannot.create.with.null.review.uuid");
    }

    public static ImageException cannotCreateWithNullImagePath() {
        return new ImageException("cannot.create.with.null.image.path");
    }

    public static ImageException cannotCreateWithEmptyImagePath() {
        return new ImageException("cannot.create.with.empty.image.path");
    }

    public static ImageException cannotCreateWithImagePathExceedMaxLength() {
        return new ImageException("cannot.create.with.image.path.exceed.max.length");
    }

    public static ImageException cannotCreateNotOwned() {
        return new ImageException("cannot.create.not.owned");
    }

    public static ImageException cannotDeleteWithNullCreatorUuid() {
        return new ImageException("cannot.delete.with.null.creator.uuid");
    }

    public static ImageException cannotDeleteWithNullProductUuid() {
        return new ImageException("cannot.delete.with.null.product.uuid");
    }

    public static ImageException cannotDeleteWithNullReviewUuid() {
        return new ImageException("cannot.delete.with.null.review.uuid");
    }

    public static ImageException cannotDeleteWithNullImageUuid() {
        return new ImageException("cannot.delete.with.null.image.uuid");
    }

    public static ImageException cannotDeleteNotOwned() {
        return new ImageException("cannot.delete.not.owned");
    }

}
