package com.alphanah.alphanahbackend.model.order;

import com.alphanah.alphanahbackend.model.account.AccountResponseM1;
import lombok.Data;

@Data
public class CartResponseM1 {
    private String orderUUID;
    private AccountResponseM1 creator;
    private String orderType;
}