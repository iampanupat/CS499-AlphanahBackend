package com.alphanah.alphanahbackend.exception;

public class AccountException extends AlphanahBaseException {

    public AccountException(String message) {
        super(message);
    }

    public static AccountException updateFirstnameNull() {
        return new AccountException("update.firstname.null");
    }

    public static AccountException updateLastnameNull() {
        return new AccountException("update.lastname.null");
    }

    public static AccountException updateAddressNull() {
        return new AccountException("update.address.null");
    }

    public static AccountException updatePhoneNull() {
        return new AccountException("update.phone.null");
    }

    public static AccountException updateFirstnameMaxLength() {
        return new AccountException("update.firstname.max.length");
    }

    public static AccountException updateLastnameMaxLength() {
        return new AccountException("update.lastname.max.length");
    }

    public static AccountException updateAddressMaxLength() {
        return new AccountException("update.address.max.length");
    }

    public static AccountException updatePhoneMaxLength() {
        return new AccountException("update.phone.max.length");
    }

}
