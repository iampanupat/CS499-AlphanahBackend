package com.alphanah.alphanahbackend.model.authentication;

import lombok.Data;

@Data
public class MLoginResponse {
    private String accessToken;
    private String idToken;
    private String refreshToken;
    private String tokenType;
    private Integer expiresIn;
}
