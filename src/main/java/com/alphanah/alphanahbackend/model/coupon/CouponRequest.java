package com.alphanah.alphanahbackend.model.coupon;

import com.alphanah.alphanahbackend.enumerate.CouponType;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class CouponRequest {
    private String code;
    private CouponType type;
    private Long value = -1L;
    private Date startDate = new Date();
    private Date endDate;
    private Integer maxUse;
}
