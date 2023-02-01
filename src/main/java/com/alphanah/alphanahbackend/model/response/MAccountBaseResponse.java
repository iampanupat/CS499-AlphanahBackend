package com.alphanah.alphanahbackend.model.response;

import com.alphanah.alphanahbackend.model.enumerate.ERole;
import lombok.Data;

import java.util.UUID;

@Data
public class MAccountBaseResponse {
    private String uuid;
    private String role;
    private String firstname;
    private String lastname;
    private String image;
}
