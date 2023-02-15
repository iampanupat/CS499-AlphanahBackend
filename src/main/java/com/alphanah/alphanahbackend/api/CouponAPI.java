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
            @RequestParam(required = false) CouponType type, @RequestParam(required = false) Boolean usage,
            @RequestParam(required = false) Boolean expired, @RequestParam(required = false) UUID merchant
    ) throws AlphanahBaseException {
        List<CouponResponseM1> rawResponses = business.getAllCoupons(type, usage, expired, merchant);
        ListResponse response = new ListResponse(rawResponses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{coupon_uuid}")
    public ResponseEntity<CouponResponseM1> getCoupon(@PathVariable("coupon_uuid") UUID couponUuid) throws AlphanahBaseException {
        CouponResponseM1 response = business.getCoupon(couponUuid);
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

    @DeleteMapping("/{coupon_uuid}")
    public ResponseEntity<Void> deleteCoupon(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("coupon_uuid") UUID couponUuid
    ) throws AlphanahBaseException {
        AccountUtils.merchantVerify(token);
        business.deleteCoupon(AccountUtils.findAccount(token).getUuid(), couponUuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}