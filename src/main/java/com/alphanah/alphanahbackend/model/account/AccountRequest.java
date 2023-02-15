package com.alphanah.alphanahbackend.model.account;

import lombok.Data;

@Data
public class AccountRequest {
    private String firstname;
    private String lastname;
    private String address;
    private String phone;
}
