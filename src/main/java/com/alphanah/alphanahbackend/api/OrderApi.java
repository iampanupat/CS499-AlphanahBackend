package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.OrderBusiness;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.ListResponse;
import com.alphanah.alphanahbackend.model.order.CartResponseM2;
import com.alphanah.alphanahbackend.model.order.PaidResponseM2;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class OrderApi {

    @Autowired
    private OrderBusiness business;

    @PostMapping("/cart/coupon/{coupon_uuid}")
    public ResponseEntity<CartResponseM2> addCoupon(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("coupon_uuid") UUID couponUuid
    ) throws AlphanahBaseException {
        CartResponseM2 response = business.updateCoupon(AccountUtils.findAccount(token).getUuid(), couponUuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/cart/coupon")
    public ResponseEntity<CartResponseM2> removeCoupon(@RequestHeader(value = "Authorization") String token) throws AlphanahBaseException {
        CartResponseM2 response = business.updateCoupon(AccountUtils.findAccount(token).getUuid(), null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<CartResponseM2> getCartOrder(@RequestHeader(value = "Authorization") String token) throws AlphanahBaseException, InterruptedException {
        CartResponseM2 response = business.getCartOrder(AccountUtils.findAccount(token).getUuid());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/purchase_order")
    public ResponseEntity<ListResponse> getAllPaidOrders(@RequestHeader(value = "Authorization") String token) throws AlphanahBaseException {
        List<PaidResponseM2> rawResponse = business.getAllPaidOrders(AccountUtils.findAccount(token).getUuid());
        ListResponse response = new ListResponse(rawResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Map<String, Object>> checkout(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody byte[] stripeRequest
    ) throws AlphanahBaseException {
        return business.checkout(AccountUtils.findAccount(token).getUuid(), stripeRequest);
    }

}
