package com.alphanah.alphanahbackend.model.order;

import lombok.Data;

@Data
public class PaidResponseM1 extends CartResponseM1 {
    private String checkoutDate;
    private String payType;
    private DeliveryInformation deliveryInformation;
}
