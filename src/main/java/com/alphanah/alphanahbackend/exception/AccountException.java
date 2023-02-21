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

    public static AccountException cannotUpdateWithEmptyFirstname() {
        return new AccountException("cannot.update.with.empty.firstname");
    }

    public static AccountException cannotUpdateWithEmptyLastname() {
        return new AccountException("cannot.update.with.empty.lastname");
    }

    public static AccountException cannotUpdateWithEmptyAddress() {
        return new AccountException("cannot.update.with.empty.address");
    }

    public static AccountException cannotUpdateWithEmptyPhone() {
        return new AccountException("cannot.update.with.empty.phone");
    }

    public static AccountException cannotUpdateWithFirstnameExceedMaxLength() {
        return new AccountException("cannot.update.with.firstname.exceed.max.length");
    }

    public static AccountException cannotUpdateWithLastnameExceedMaxLength() {
        return new AccountException("cannot.update.with.lastname.exceed.max.length");
    }

    public static AccountException cannotUpdateWithAddressExceedMaxLength() {
        return new AccountException("cannot.update.with.address.exceed.max.length");
    }

    public static AccountException cannotUpdateWithPhoneExceedMaxLength() {
        return new AccountException("cannot.update.with.phone.exceed.max.length");
    }

    public static AccountException cannotUpdateWithNullAwsCognitoField() {
        return new AccountException("cannot.update.with.null.aws.cognito.field");
    }

    public static AccountException cannotUpdateWithNullValue() {
        return new AccountException("cannot.update.with.null.value");
    }

    public static AccountException cannotUpdateWithAwsCognitoValueExceedMaxLength() {
        return new AccountException("cannot.update.with.aws.cognito.value.exceed.max.length");
    }

}