package com.alphanah.alphanahbackend.model.authentication;

import com.alphanah.alphanahbackend.enumerate.Role;
import lombok.Data;

@Data
public class RegisterResponse {
    private String email;
    private Role role;
}
