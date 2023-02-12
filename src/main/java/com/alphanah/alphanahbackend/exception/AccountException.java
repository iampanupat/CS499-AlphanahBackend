package com.alphanah.alphanahbackend.exception;

public class AccountException extends AlphanahBaseException {

    public AccountException(String message) {
        super("account." + message);
    }

    public static AccountException getWithNullToken() {
        return new AccountException("get.with.null.token");
    }

    public static AccountException getWithNullUuid() {
        return new AccountException("get.with.null.uuid");
    }

    public static AccountException getNullObject() {
        return new AccountException("get.null.object");
    }

    public static AccountException updateWithNullToken() {
        return new AccountException("update.with.null.token");
    }

    public static AccountException updateWithNullUuid() {
        return new AccountException("update.with.null.uuid");
    }

    public static AccountException updateWithNullCognitoField() {
        return new AccountException("update.with.null.cognito.field");
    }

    public static AccountException updateWithNullValue() {
        return new AccountException("update.with.null.value");
    }

    public static AccountException updateWithEmptyValue() {
        return new AccountException("update.with.empty.value");
    }

    public static AccountException updateWithMaxLengthValue() {
        return new AccountException("update.with.max.length.value");
    }

    public static AccountException updateNullObject() {
        return new AccountException("update.null.object");
    }

    public static AccountException updateWithNullFirstname() {
        return new AccountException("update.with.null.firstname");
    }

    public static AccountException updateWithNullLastname() {
        return new AccountException("update.with.null.lastname");
    }

    public static AccountException updateWithNullAddress() {
        return new AccountException("update.with.null.address");
    }

    public static AccountException updateWithNullPhone() {
        return new AccountException("update.with.null.phone");
    }

    public static AccountException updateWithMaxLengthFirstname() {
        return new AccountException("update.with.max.length.firstname");
    }

    public static AccountException updateWithMaxLengthLastname() {
        return new AccountException("updateWithMaxLengthLastname");
    }

    public static AccountException updateWithMaxLengthAddress() {
        return new AccountException("update.with.max.length.address");
    }

    public static AccountException updateWithMaxLengthPhone() {
        return new AccountException("update.with.max.length.phone");
    }

}