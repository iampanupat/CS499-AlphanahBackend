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

    public static CategoryException updateWithNullCreatorUuid() {
        return new CategoryException("update.with.null.creator.uuid");
    }

    public static CategoryException updateWithNullUuid() {
        return new CategoryException("update.with.null.uuid");
    }

    public static CategoryException updateWithNullName() {
        return new CategoryException("update.with.null.name");
    }

    public static CategoryException updateWithEmptyName() {
        return new CategoryException("update.with.empty.name");
    }

    public static CategoryException updateWithMaxLengthName() {
        return new CategoryException("update.with.max.length.name");
    }

    public static CategoryException updateDuplicateName() {
        return new CategoryException("update.duplicate.name");
    }

    public static CategoryException updateNullObject() {
        return new CategoryException("update.null.object");
    }

    public static CategoryException updateNotOwned() {
        return new CategoryException("update.not.owned");
    }

    public static CategoryException deleteWithNullCreatorUuid() {
        return new CategoryException("delete.with.null.creator.uuid");
    }

    public static CategoryException deleteWithNullUuid() {
        return new CategoryException("delete.with.null.uuid");
    }

    public static CategoryException deleteNullObject() {
        return new CategoryException("delete.null.object");
    }

    public static CategoryException deleteNotOwned() {
        return new CategoryException("delete.not.owned");
    }

}
