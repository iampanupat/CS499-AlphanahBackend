package com.alphanah.alphanahbackend.model.authentication;

import lombok.Data;

@Data
public class MRegisterRequest {
    private String email;
    private String password;
    private String confirmPassword;
}
