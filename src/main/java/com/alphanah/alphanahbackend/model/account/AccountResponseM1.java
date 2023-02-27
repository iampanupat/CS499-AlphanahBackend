package com.alphanah.alphanahbackend.model.account;

import lombok.Data;

import java.util.Date;

@Data
public class AccountResponseM1 {
    private String accountUUID;
    private String role;
    private String firstname;
    private String lastname;
    private String image;
    private String createDate;
    private Long productCount;
    private Long reviewCount;
}
