package com.alphanah.alphanahbackend.model.authentication;

import com.alphanah.alphanahbackend.model.account.AccountResponseM2;
import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;
    private String idToken;
    private String refreshToken;
    private String tokenType;
    private Integer expiresIn;
    private AccountResponseM2 account;
}
