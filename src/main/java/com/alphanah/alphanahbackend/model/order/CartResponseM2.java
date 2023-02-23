package com.alphanah.alphanahbackend.model.order;

import com.alphanah.alphanahbackend.model.coupon.CouponResponseM1;
import com.alphanah.alphanahbackend.model.order_item.CartItemResponseM2;
import lombok.Data;

import java.util.List;

@Data
public class CartResponseM2 extends CartResponseM1 {
    private Boolean discount;
    private CouponResponseM1 coupon;
    private Double rawTotalPrice = 0.0;
    private Double totalPrice = 0.0;
    private Double rawDeliveryFee = 0.0;
    private Double deliveryFee = 0.0;
    private List<CartItemResponseM2> cartItems;
}
