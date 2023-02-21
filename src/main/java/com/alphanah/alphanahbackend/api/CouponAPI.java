package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.CouponBusiness;
import com.alphanah.alphanahbackend.enumerate.CouponType;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.ListResponse;
import com.alphanah.alphanahbackend.model.coupon.CouponRequest;
import com.alphanah.alphanahbackend.model.coupon.CouponResponseM1;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/coupon")
public class CouponAPI {

    @Autowired
    private CouponBusiness business;

    @GetMapping
    public ResponseEntity<ListResponse> getAllCouponsWithUsage(
            @RequestParam(required = false) CouponType type,
            @RequestParam(required = false) Boolean started,
            @RequestParam(required = false) Boolean expired,
            @RequestParam(required = false) Boolean runOut,
            @RequestParam(required = false) UUID merchant
    ) throws AlphanahBaseException {
        List<CouponResponseM1> rawResponses = business.getAllCoupons(type, started, expired, runOut, merchant);
        ListResponse response = new ListResponse(rawResponses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{coupon_code}")
    public ResponseEntity<CouponResponseM1> getCoupon(@PathVariable("coupon_code") String couponCode) throws AlphanahBaseException {
        CouponResponseM1 response = business.getCoupon(couponCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CouponResponseM1> createCoupon(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody CouponRequest request
    ) throws AlphanahBaseException {
        AccountUtils.merchantVerify(token);
        CouponResponseM1 response = business.createCoupon(AccountUtils.findAccount(token).getUuid(), request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{coupon_code}")
    public ResponseEntity<Void> deleteCoupon(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("coupon_code") String couponCode
    ) throws AlphanahBaseException {
        AccountUtils.merchantVerify(token);
        business.deleteCoupon(AccountUtils.findAccount(token).getUuid(), couponCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}