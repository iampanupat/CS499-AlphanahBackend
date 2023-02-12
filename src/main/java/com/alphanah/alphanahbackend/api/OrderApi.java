package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.OrderBusiness;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.order.CartResponseM2;
import com.alphanah.alphanahbackend.model.order.PaidResponseM2;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OrderApi {

    @Autowired
    private OrderBusiness business;

    @GetMapping("/cart")
    public ResponseEntity<CartResponseM2> getCartOrder(@RequestHeader(value = "Authorization") String token) throws AlphanahBaseException {
        CartResponseM2 response = business.getCartOrder(AccountUtils.getAccountWithToken(token).getUuid());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/purchase_order")
    public ResponseEntity<List<PaidResponseM2>> getAllPaidOrders(@RequestHeader(value = "Authorization") String token) throws AlphanahBaseException {
        List<PaidResponseM2> responses = business.getAllPaidOrders(AccountUtils.getAccountWithToken(token).getUuid());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Map<String, Object>> checkout(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody byte[] stripeRequest
    ) throws AlphanahBaseException {
        return business.checkout(AccountUtils.getAccountWithToken(token).getUuid(), stripeRequest);
    }

}
