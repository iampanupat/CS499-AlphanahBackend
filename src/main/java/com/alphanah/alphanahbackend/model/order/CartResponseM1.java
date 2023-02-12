package com.alphanah.alphanahbackend.model.order;

import lombok.Data;

@Data
public class CartResponseM1 {
    private String orderUuid;
    private String creatorUuid;
    private String orderType;
}
