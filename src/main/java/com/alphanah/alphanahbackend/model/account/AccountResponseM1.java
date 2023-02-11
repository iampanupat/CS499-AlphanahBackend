package com.alphanah.alphanahbackend.model.account;

import lombok.Data;

import java.util.Date;

@Data
public class AccountResponseM1 {
    private String uuid;
    private String role;
    private String firstname;
    private String lastname;
    private String image;
    private String createDate;
}
