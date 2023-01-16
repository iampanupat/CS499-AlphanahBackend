package com.alphanah.alphanahbackend.model.authentication;

import lombok.Data;

@Data
public class MLoginRequest {
    private String email;
    private String password;
}
