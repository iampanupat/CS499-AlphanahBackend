package com.alphanah.alphanahbackend.exception;

public class ReviewException extends AlphanahBaseException {

    public ReviewException(String message) {
        super("review." + message);
    }

    public static ReviewException cannotFindWithNullProductUuid() {
        return new ReviewException("cannot.find.with.null.product.uuid");
    }

    public static ReviewException cannotFindWithNullReviewUuid() {
        return new ReviewException("cannot.find.with.null.review.uuid");
    }

    public static ReviewException notFound() {
        return new ReviewException("not.found");
    }

    public static ReviewException cannotFindWithInvalidProductUuid() {
        return new ReviewException("cannot.find.with.invalid.product.uuid");
    }

    public static ReviewException cannotCreateWithNullCreatorUuid() {
        return new ReviewException("cannot.create.with.null.creator.uuid");
    }

    public static ReviewException cannotCreateWithNullProductUuid() {
        return new ReviewException("cannot.create.with.null.product.uuid");
    }

    public static ReviewException cannotCreateWithNullMessage() {
        return new ReviewException("cannot.create.with.null.message");
    }

    public static ReviewException cannotCreateWithNullRating() {
        return new ReviewException("cannot.create.with.null.rating");
    }

    public static ReviewException cannotCreateWithEmptyMessage() {
        return new ReviewException("cannot.create.with.empty.message");
    }

    public static ReviewException cannotCreateWithRatingLessMinValue() {
        return new ReviewException("cannot.create.with.rating.less.min.value");
    }

    public static ReviewException cannotCreateWithMessageExceedMaxLength() {
        return new ReviewException("cannot.create.with.message.exceed.max.length");
    }

    public static ReviewException cannotCreateWithRatingExceedMaxValue() {
        return new ReviewException("cannot.create.with.rating.exceed.max.value");
    }

    public static ReviewException cannotCreateTwiceWithOneAccount() {
        return new ReviewException("cannot.create.twice.with.one.account");
    }

    public static ReviewException cannotUpdateWithNullCreatorUuid() {
        return new ReviewException("cannot.update.with.null.creator.uuid");
    }

    public static ReviewException cannotUpdateWithNullProductUuid() {
        return new ReviewException("cannot.update.with.null.product.uuid");
    }

    public static ReviewException cannotUpdateWithNullReviewUuid() {
        return new ReviewException("cannot.update.with.null.review.uuid");
    }

    public static ReviewException cannotUpdateWithNullMessage() {
        return new ReviewException("cannot.update.with.null.message");
    }

    public static ReviewException cannotUpdateWithNullRating() {
        return new ReviewException("cannot.update.with.null.rating");
    }

    public static ReviewException cannotUpdateWithEmptyMessage() {
        return new ReviewException("cannot.update.with.empty.message");
    }

    public static ReviewException cannotUpdateWithRatingLessMinValue() {
        return new ReviewException("cannot.update.with.rating.less.min.value");
    }

    public static ReviewException cannotUpdateWithMessageExceedMaxLength() {
        return new ReviewException("cannot.update.with.message.exceed.max.length");
    }

    public static ReviewException cannotUpdateWithRatingExceedMaxValue() {
        return new ReviewException("cannot.update.with.rating.exceed.max.value");
    }

    public static ReviewException cannotUpdateNotOwned() {
        return new ReviewException("cannot.update.not.owned");
    }

    public static ReviewException cannotDeleteWithNullCreatorUuid() {
        return new ReviewException("cannot.delete.with.null.creator.uuid");
    }

    public static ReviewException cannotDeleteWithNullProductUuid() {
        return new ReviewException("cannot.delete.with.null.product.uuid");
    }

    public static ReviewException cannotDeleteWithNullReviewUuid() {
        return new ReviewException("cannot.delete.with.null.review.uuid");
    }

    public static ReviewException cannotDeleteNotOwned() {
        return new ReviewException("cannot.delete.not.owned");
    }

}