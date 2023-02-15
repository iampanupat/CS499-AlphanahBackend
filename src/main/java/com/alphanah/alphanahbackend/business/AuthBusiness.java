package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.exception.AuthException;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.authentication.LoginRequest;
import com.alphanah.alphanahbackend.model.authentication.LoginResponse;
import com.alphanah.alphanahbackend.model.authentication.RegisterRequest;
import com.alphanah.alphanahbackend.model.authentication.RegisterResponse;
import com.alphanah.alphanahbackend.enumerate.Role;
import com.alphanah.alphanahbackend.service.AuthService;
import com.amazonaws.services.cognitoidp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class AuthBusiness {

    @Autowired
    private AuthService service;

    private static final int EMAIL_MAX_LENGTH = 128;
    private static final int PASSWORD_MAX_LENGTH = 256;

    public RegisterResponse register(RegisterRequest request, Role role) throws AlphanahBaseException {
        if (request == null)
            throw AuthException.registerRequestNull();

        if (role == null)
            throw AuthException.registerRoleNull();

        if (Objects.isNull(request.getEmail()))
            throw AuthException.registerEmailNull();

        if (Objects.isNull(request.getPassword()))
            throw AuthException.registerPasswordNull();

        if (Objects.isNull(request.getConfirmPassword()))
            throw AuthException.registerConfirmPasswordNull();

        if (request.getEmail().isEmpty())
            throw AuthException.registerEmailEmpty();

        if (request.getPassword().isEmpty())
            throw AuthException.registerPasswordEmpty();

        if (request.getConfirmPassword().isEmpty())
            throw AuthException.registerConfirmPasswordEmpty();

        if (request.getEmail().length() > EMAIL_MAX_LENGTH)
            throw AuthException.registerEmailMaxLength();

        if (request.getPassword().length() > PASSWORD_MAX_LENGTH)
            throw AuthException.registerPasswordMaxLength();

        if (request.getConfirmPassword().length() > PASSWORD_MAX_LENGTH)
            throw AuthException.registerConfirmPasswordMaxLength();

        if ( !(Objects.equals(request.getPassword(), request.getConfirmPassword())) )
            throw AuthException.registerPasswordsNotMatch();

        Map<String, String> accountDetail = service.createAccount(request.getEmail(), request.getPassword(), role);
        RegisterResponse response = new RegisterResponse();
        response.setEmail(accountDetail.get("email"));
        response.setRole(Role.valueOf(accountDetail.get("role")));
        return response;
    }

    public LoginResponse login(LoginRequest request) throws AlphanahBaseException {
        if (request == null)
            throw AuthException.loginRequestNull();

        if (Objects.isNull(request.getEmail()))
            throw AuthException.loginEmailNull();

        if (Objects.isNull(request.getPassword()))
            throw AuthException.loginPasswordNull();

        if (request.getEmail().isEmpty())
            throw AuthException.loginEmailEmpty();

        if (request.getPassword().isEmpty())
            throw AuthException.loginPasswordEmpty();

        if (request.getEmail().length() > EMAIL_MAX_LENGTH)
            throw AuthException.loginEmailMaxLength();

        if (request.getPassword().length() > PASSWORD_MAX_LENGTH)
            throw AuthException.loginPasswordMaxLength();

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
