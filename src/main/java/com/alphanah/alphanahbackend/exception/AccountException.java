package com.alphanah.alphanahbackend.exception;

public class AccountException extends AlphanahBaseException {

    public AccountException(String message) {
        super("account." + message);
    }

    public static AccountException cannotFindByNullToken() {
        return new AccountException("cannot.find.by.null.token");
    }

    public static AccountException cannotFindByNullAccountUuid() {
        return new AccountException("cannot.find.by.null.account.uuid");
    }

    public static AccountException notFound() {
        return new AccountException("not.found");
    }

    public static AccountException cannotUpdateWithNullToken() {
        return new AccountException("cannot.update.with.null.token");
    }

    public static AccountException cannotUpdateWithNullAccountUuid() {
        return new AccountException("cannot.update.with.null.account.uuid");
    }

    public static AccountException cannotUpdateWithNullAccount() {
        return new AccountException("cannot.update.with.null.account");
    }

    public static AccountException cannotUpdateWithNullFirstname() {
        return new AccountException("cannot.update.with.null.firstname");
    }

    public static AccountException cannotUpdateWithNullLastname() {
        return new AccountException("cannot.update.with.null.lastname");
    }

    public static AccountException cannotUpdateWithNullAddress() {
        return new AccountException("cannot.update.with.null.address");
    }

    public static AccountException cannotUpdateWithNullPhone() {
        return new AccountException("cannot.update.with.null.phone");
    }

    public static AccountException cannotUpdateWithOverFirstnameMaxLength() {
        return new AccountException("cannot.update.with.over.firstname.max.length");
    }

    public static AccountException cannotUpdateWithOverLastnameMaxLength() {
        return new AccountException("cannot.update.with.over.lastname.max.length");
    }

    public static AccountException cannotUpdateWithOverAddressMaxLength() {
        return new AccountException("cannot.update.with.over.address.max.length");
    }

    public static AccountException cannotUpdateWithOverPhoneMaxLength() {
        return new AccountException("cannot.update.with.over.phone.max.length");
    }

    public static AccountException cannotUpdateWithNullAwsCognitoField() {
        return new AccountException("cannot.update.with.null.aws.cognito.field");
    }

    public static AccountException cannotUpdateWithNullValue() {
        return new AccountException("cannot.update.with.null.value");
    }

    public static AccountException cannotUpdateWithOverAwsCognitoValueMaxLength() {
        return new AccountException("cannot.update.with.over.aws.cognito.value.max.length");
    }

}