package com.alphanah.alphanahbackend.model.coupon;

import com.alphanah.alphanahbackend.enumerate.CouponType;
import com.alphanah.alphanahbackend.model.account.AccountResponseM1;
import lombok.Data;

import java.util.UUID;

@Data
public class CouponResponseM1 {
    private String couponCode;
    private CouponType type;
    private Long value;
    private String startDate;
    private String endDate;
    private AccountResponseM1 creator;
    private Integer maxUse;
    private Integer useCount;
}
