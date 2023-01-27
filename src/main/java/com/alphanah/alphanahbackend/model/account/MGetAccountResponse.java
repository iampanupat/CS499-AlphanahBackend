package com.alphanah.alphanahbackend.model.account;

import com.alphanah.alphanahbackend.model.enumerate.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class MGetAccountResponse {
    private UUID uuid;
    private String email;
    private Role role;
    private String firstname;
    private String lastname;
    private String address;
    private String phone;
    private String picture;
    private UUID cartUuid;
}
