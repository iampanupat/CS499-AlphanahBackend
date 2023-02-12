package com.alphanah.alphanahbackend.model.order_item;

import lombok.Data;

@Data
public class PaidItemResponseM1 extends CartItemResponseM1 {
    private String price;
    private String deliveryStatus;
}
