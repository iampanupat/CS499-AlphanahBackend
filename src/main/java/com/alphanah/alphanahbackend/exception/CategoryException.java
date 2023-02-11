package com.alphanah.alphanahbackend.exception;

public class CategoryException extends AlphanahBaseException {

    public CategoryException(String message) {
        super("category." + message);
    }

    public static CategoryException getWithNullUuid() {
        return new CategoryException("get.with.null.uuid");
    }

    public static CategoryException getNullObject() {
        return new CategoryException("get.null.object");
    }

    public static CategoryException createWithNullCreatorUuid() {
        return new CategoryException("create.with.null.creator.uuid");
    }

    public static CategoryException createWithNullName() {
        return new CategoryException("create.with.null.name");
    }

    public static CategoryException createWithEmptyName() {
        return new CategoryException("create.with.empty.name");
    }

    public static CategoryException createWithMaxLengthName() {
        return new CategoryException("create.with.max.length.name");
    }

    public static CategoryException createDuplicateName() {
        return new CategoryException("create.duplicate.name");
    }

    public static CategoryException createWithNullParentObject() {
        return new CategoryException("create.with.null.parent.object");
    }

}
