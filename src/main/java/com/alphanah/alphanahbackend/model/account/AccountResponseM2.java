package com.alphanah.alphanahbackend.model.account;

import lombok.Data;

@Data
public class AccountResponseM2 extends AccountResponseM1 {
    private String email;
    private String address;
    private String phone;
    private String cartUuid;
}
