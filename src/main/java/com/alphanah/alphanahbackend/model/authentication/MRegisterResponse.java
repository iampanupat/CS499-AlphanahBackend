package com.alphanah.alphanahbackend.model.authentication;

import com.alphanah.alphanahbackend.model.enumerate.ERole;
import lombok.Data;

@Data
public class MRegisterResponse {
    private String email;
    private ERole ERole;
}
