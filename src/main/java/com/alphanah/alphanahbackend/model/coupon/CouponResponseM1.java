package com.alphanah.alphanahbackend.model.coupon;

import com.alphanah.alphanahbackend.enumerate.CouponType;
import com.alphanah.alphanahbackend.model.account.AccountResponseM1;
import lombok.Data;

import java.util.UUID;

@Data
public class CouponResponseM1 {
    private UUID couponUUID;
    private CouponType type;
    private Long value;
    private Boolean usageStatus;
    private String expiredDate;
    private AccountResponseM1 creator;
}
