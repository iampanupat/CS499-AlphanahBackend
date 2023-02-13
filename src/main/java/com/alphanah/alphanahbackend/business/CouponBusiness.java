package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.Coupon;
import com.alphanah.alphanahbackend.enumerate.CouponType;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.coupon.CouponRequest;
import com.alphanah.alphanahbackend.model.coupon.CouponResponseM1;
import com.alphanah.alphanahbackend.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class CouponBusiness {

    @Autowired
    private CouponService service;

    public List<CouponResponseM1> getAllCoupons(CouponType type, Boolean usage, Boolean expired, UUID merchant) throws AlphanahBaseException {
        List<CouponResponseM1> responses = new ArrayList<>();
        List<Coupon> couponList = service.findAllCoupons();
        for (Coupon coupon: couponList) {
            if (!Objects.isNull(type) && !coupon.getType().equals(type))
                continue;
            if (!Objects.isNull(usage) && (coupon.isUsed() != usage))
                continue;
            if (!Objects.isNull(expired) && (coupon.isExpired() != expired))
                continue;
            if (!Objects.isNull(merchant) && !coupon.getCreatorUuid().equals(merchant))
                continue;
            responses.add(coupon.toCouponResponseM1());
        }
        return responses;
    }

    public CouponResponseM1 getCoupon(UUID couponUuid) throws AlphanahBaseException {
        Coupon coupon = service.findCoupon(couponUuid);
        return coupon.toCouponResponseM1();
    }

    public CouponResponseM1 createCoupon(UUID creatorUuid, CouponRequest request) throws AlphanahBaseException {
        Coupon coupon = service.createCoupon(creatorUuid, request.getType(), request.getValue(), request.getExpiredDate());
        return coupon.toCouponResponseM1();
    }

    public void deleteCoupon(UUID creatorUuid, UUID couponUuid) throws AlphanahBaseException {
        service.deleteCoupon(creatorUuid, couponUuid);
    }

}
