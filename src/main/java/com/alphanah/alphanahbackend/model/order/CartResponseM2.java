package com.alphanah.alphanahbackend.model.order;

import com.alphanah.alphanahbackend.model.order_item.CartItemResponseM2;
import lombok.Data;

import java.util.List;

@Data
public class CartResponseM2 extends CartResponseM1 {
    private String totalPrice;
    private String totalQuantity;
    private List<CartItemResponseM2> cartItems;
}
