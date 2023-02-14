package com.alphanah.alphanahbackend.model.authentication;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String confirmPassword;
}
