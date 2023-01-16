package com.alphanah.alphanahbackend.model.authentication;

import com.alphanah.alphanahbackend.model.enumerate.EAccountRole;
import lombok.Data;

@Data
public class MRegisterResponse {
    private String email;
    private EAccountRole role;
}
