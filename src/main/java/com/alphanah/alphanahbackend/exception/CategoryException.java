package com.alphanah.alphanahbackend.exception;

public class CategoryException extends AlphanahBaseException {

    public CategoryException(String message) {
        super("category." + message);
    }

    public static CategoryException cannotFindWithNullCategoryUuid() {
        return new CategoryException("cannot.find.with.null.category.uuid");
    }

    public static CategoryException notFound() {
        return new CategoryException("not.found");
    }

    public static CategoryException cannotCreateWithNullName() {
        return new CategoryException("cannot.create.with.null.name");
    }

    public static CategoryException cannotCreateWithEmptyName() {
        return new CategoryException("cannot.create.with.empty.name");
    }

    public static CategoryException cannotCreateWithNameExceedMaxLength() {
        return new CategoryException("cannot.create.with.name.exceed.max.length");
    }

    public static CategoryException cannotCreateWithDuplicateName() {
        return new CategoryException("cannot.create.with.duplicate.name");
    }

}
