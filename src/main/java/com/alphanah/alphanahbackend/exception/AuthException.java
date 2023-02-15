package com.alphanah.alphanahbackend.exception;

public class AuthException extends AlphanahBaseException {

    public AuthException(String message) {
        super("auth." + message);
    }

    public static AuthException roleNotAllowed() {
        return new AuthException("role.not.allowed");
    }

    public static AuthException cannotRegisterWithNullEmail() {
        return new AuthException("cannot.register.with.null.email");
    }

    public static AuthException cannotRegisterWithNullPassword() {
        return new AuthException("cannot.register.with.null.password");
    }

    public static AuthException cannotRegisterWithNullConfirmPassword() {
        return new AuthException("cannot.register.with.null.confirm.password");
    }

    public static AuthException cannotRegisterWithNullRole() {
        return new AuthException("cannot.register.with.null.role");
    }

    public static AuthException cannotRegisterWithEmptyEmail() {
        return new AuthException("cannot.register.with.empty.email");
    }

    public static AuthException cannotRegisterWithEmptyPassword() {
        return new AuthException("cannot.register.with.empty.password");
    }

    public static AuthException cannotRegisterWithEmptyConfirmPassword() {
        return new AuthException("cannot.register.with.empty.confirm.password");
    }

    public static AuthException cannotRegisterWithEmailExceedMaxLength() {
        return new AuthException("cannot.register.with.email.exceed.max.length");
    }

    public static AuthException cannotRegisterWithPasswordExceedMaxLength() {
        return new AuthException("cannot.register.with.password.exceed.max.length");
    }

    public static AuthException cannotRegisterWithConfirmPasswordExceedMaxLength() {
        return new AuthException("cannot.register.with.confirm.password.exceed.max.length");
    }

    public static AuthException cannotRegisterWithPasswordsNotMatch() {
        return new AuthException("cannot.register.with.passwords.not.match");
    }

    public static AuthException cannotLoginWithNullEmail() {
        return new AuthException("cannot.login.with.null.email");
    }

    public static AuthException cannotLoginWithNullPassword() {
        return new AuthException("cannot.login.with.null.password");
    }

    public static AuthException cannotLoginWithEmptyEmail() {
        return new AuthException("cannot.login.with.empty.email");
    }

    public static AuthException cannotLoginWithEmptyPassword() {
        return new AuthException("cannot.login.with.empty.password");
    }

    public static AuthException cannotLoginWithEmailExceedMaxLength() {
        return new AuthException("cannot.login.with.email.exceed.max.length");
    }

    public static AuthException cannotLoginWithPasswordExceedMaxLength() {
        return new AuthException("cannot.login.with.password.exceed.max.length");
    }

}