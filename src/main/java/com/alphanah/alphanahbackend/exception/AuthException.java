package com.alphanah.alphanahbackend.exception;

public class AuthException extends AlphanahBaseException {

    public AuthException(String message) {
        super("auth." + message);
    }

    public static AuthException registerRequestNull() {
        return new AuthException("register.request.null");
    }

    public static AuthException registerRoleNull() {
        return new AuthException("register.role.null");
    }

    public static AuthException registerEmailNull() {
        return new AuthException("register.email.null");
    }

    public static AuthException registerEmailEmpty() {
        return new AuthException("register.email.empty");
    }

    public static AuthException registerEmailMaxLength() {
        return new AuthException("register.email.max.length");
    }

    public static AuthException registerPasswordNull() {
        return new AuthException("register.password.null");
    }

    public static AuthException registerPasswordEmpty() {
        return new AuthException("register.password.empty");
    }

    public static AuthException registerPasswordMaxLength() {
        return new AuthException("register.password.max.length");
    }

    public static AuthException registerConfirmPasswordNull() {
        return new AuthException("register.confirm.password.null");
    }

    public static AuthException registerConfirmPasswordEmpty() {
        return new AuthException("register.confirm.password.empty");
    }

    public static AuthException registerConfirmPasswordMaxLength() {
        return new AuthException("register.confirm.password.max.length");
    }

    public static AuthException registerPasswordsNotMatch() {
        return new AuthException("register.passwords.not.match");
    }

    public static AuthException loginRequestNull() {
        return new AuthException("login.request.null");
    }

    public static AuthException loginEmailNull() {
        return new AuthException("login.email.null");
    }

    public static AuthException loginEmailEmpty() {
        return new AuthException("login.email.empty");
    }

    public static AuthException loginEmailMaxLength() {
        return new AuthException("login.email.max.length");
    }

    public static AuthException loginPasswordNull() {
        return new AuthException("login.password.null");
    }

    public static AuthException loginPasswordEmpty() {
        return new AuthException("login.password.empty");
    }

    public static AuthException loginPasswordMaxLength() {
        return new AuthException("login.password.max.length");
    }

}
