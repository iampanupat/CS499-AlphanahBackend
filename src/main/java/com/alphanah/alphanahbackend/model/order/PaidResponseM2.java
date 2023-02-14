package com.alphanah.alphanahbackend.model.order;

import com.alphanah.alphanahbackend.model.coupon.CouponResponseM1;
import com.alphanah.alphanahbackend.model.order_item.CartItemResponseM2;
import com.alphanah.alphanahbackend.model.order_item.PaidItemResponseM2;
import lombok.Data;

import java.util.List;

@Data
public class PaidResponseM2 extends PaidResponseM1 {
    private Boolean discount;
    private CouponResponseM1 coupon;
    private Double totalPrice;
    private Double deliveryFee;
    private List<PaidItemResponseM2> orderItems;
}
