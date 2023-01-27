package com.alphanah.alphanahbackend.model.authentication;

import com.alphanah.alphanahbackend.model.enumerate.Role;
import lombok.Data;

@Data
public class MRegisterResponse {
    private String email;
    private Role role;
}
