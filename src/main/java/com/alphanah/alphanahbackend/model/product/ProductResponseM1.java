package com.alphanah.alphanahbackend.model.product;

import com.alphanah.alphanahbackend.model.account.AccountResponseM1;
import lombok.Data;

@Data
public class ProductResponseM1 {
    private String productUUID;
    private String name;
    private String description;
    private String createDate;
    private AccountResponseM1 creator;
}
