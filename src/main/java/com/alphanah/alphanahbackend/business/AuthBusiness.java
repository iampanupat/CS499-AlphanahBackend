package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.exception.AuthException;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.authentication.LoginRequest;
import com.alphanah.alphanahbackend.model.authentication.LoginResponse;
import com.alphanah.alphanahbackend.model.authentication.RegisterRequest;
import com.alphanah.alphanahbackend.model.authentication.RegisterResponse;
import com.alphanah.alphanahbackend.enumerate.Role;
import com.alphanah.alphanahbackend.service.AuthService;
import com.alphanah.alphanahbackend.utility.Env;
import com.amazonaws.services.cognitoidp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class AuthBusiness {

    @Autowired
    private AuthService service;

    public RegisterResponse register(RegisterRequest request, Role role) throws AlphanahBaseException {
        if (Objects.isNull(request.getEmail()))
            throw AuthException.cannotRegisterWithNullEmail();

        if (Objects.isNull(request.getPassword()))
            throw AuthException.cannotRegisterWithNullPassword();

        if (Objects.isNull(request.getConfirmPassword()))
            throw AuthException.cannotRegisterWithNullConfirmPassword();

        if (role == null)
            throw AuthException.cannotRegisterWithNullRole();

        if (request.getEmail().isEmpty())
            throw AuthException.cannotRegisterWithEmptyEmail();

        if (request.getPassword().isEmpty())
            throw AuthException.cannotRegisterWithEmptyPassword();

        if (request.getConfirmPassword().isEmpty())
            throw AuthException.cannotRegisterWithEmptyConfirmPassword();

        if (request.getEmail().length() > Env.EMAIL_MAX_LENGTH)
            throw AuthException.cannotRegisterWithEmailExceedMaxLength();

        if (request.getPassword().length() > Env.PASSWORD_MAX_LENGTH)
            throw AuthException.cannotRegisterWithPasswordExceedMaxLength();

        if (request.getConfirmPassword().length() > Env.PASSWORD_MAX_LENGTH)
            throw AuthException.cannotRegisterWithConfirmPasswordExceedMaxLength();

        if ( !(Objects.equals(request.getPassword(), request.getConfirmPassword())) )
            throw AuthException.cannotRegisterWithPasswordsNotMatch();

        Map<String, String> accountDetail = service.createAccount(request.getEmail(), request.getPassword(), role);
        RegisterResponse response = new RegisterResponse();
        response.setEmail(accountDetail.get("email"));
        response.setRole(Role.valueOf(accountDetail.get("role")));
        return response;
    }

    public LoginResponse login(LoginRequest request) throws AlphanahBaseException {
        if (Objects.isNull(request.getEmail()))
            throw AuthException.cannotLoginWithNullEmail();

        if (Objects.isNull(request.getPassword()))
            throw AuthException.cannotLoginWithNullPassword();

        if (request.getEmail().isEmpty())
            throw AuthException.cannotLoginWithEmptyEmail();

        if (request.getPassword().isEmpty())
            throw AuthException.cannotLoginWithEmptyPassword();

        if (request.getEmail().length() > Env.EMAIL_MAX_LENGTH)
            throw AuthException.cannotLoginWithEmailExceedMaxLength();

        if (request.getPassword().length() > Env.PASSWORD_MAX_LENGTH)
            throw AuthException.cannotLoginWithPasswordExceedMaxLength();

        AuthenticationResultType authenticationResult = service.signInAccount(request.getEmail(), request.getPassword());
        LoginResponse response = new LoginResponse();
        response.setAccessToken(authenticationResult.getAccessToken());
        response.setIdToken(authenticationResult.getIdToken());
        response.setRefreshToken(authenticationResult.getRefreshToken());
        response.setTokenType(authenticationResult.getTokenType());
        response.setExpiresIn(authenticationResult.getExpiresIn());
        return response;
    }

}