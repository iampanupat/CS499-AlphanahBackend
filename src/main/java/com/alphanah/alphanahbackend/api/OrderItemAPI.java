package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.OrderItemBusiness;
import com.alphanah.alphanahbackend.enumerate.DeliveryStatus;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.ListResponse;
import com.alphanah.alphanahbackend.model.order.CartResponseM2;
import com.alphanah.alphanahbackend.model.order_item.CartItemResponseM2;
import com.alphanah.alphanahbackend.model.order_item.OrderItemRequest;
import com.alphanah.alphanahbackend.model.order_item.PaidItemResponseM2;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderItemAPI {

    @Autowired
    private OrderItemBusiness business;

    @PostMapping("/cart/{product_uuid}/option/{product_option_uuid}")
    public ResponseEntity<CartResponseM2> addOrCreateCartItem(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("product_uuid") UUID productUuid,
            @PathVariable("product_option_uuid") UUID optionUuid,
            @RequestBody OrderItemRequest request
    ) throws AlphanahBaseException {
        AccountUtils.customerVerify(token);
        CartResponseM2 response = business.addOrCreateCartItem(AccountUtils.findAccount(token).getUuid(), productUuid, optionUuid, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/cart/{product_uuid}/option/{product_option_uuid}")
    public ResponseEntity<CartResponseM2> updateOrCreateCartItem(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("product_uuid") UUID productUuid,
            @PathVariable("product_option_uuid") UUID optionUuid,
            @RequestBody OrderItemRequest request
    ) throws AlphanahBaseException {
        AccountUtils.customerVerify(token);
        CartResponseM2 response = business.updateOrCreateCartItem(AccountUtils.findAccount(token).getUuid(), productUuid, optionUuid, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/cart/{product_uuid}/option/{product_option_uuid}")
    public ResponseEntity<CartResponseM2> deleteCartItem(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("product_uuid") UUID productUuid,
            @PathVariable("product_option_uuid") UUID optionUuid
    ) throws AlphanahBaseException {
        AccountUtils.customerVerify(token);
        CartResponseM2 response = business.deleteCartItem(AccountUtils.findAccount(token).getUuid(), productUuid, optionUuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/sale_order")
    public ResponseEntity<ListResponse> getAllSaleOrderItem(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(required = false) DeliveryStatus deliveryStatus
    ) throws AlphanahBaseException {
        AccountUtils.merchantVerify(token);
        List<PaidItemResponseM2> rawResponse = business.getAllSaleOrderItem(AccountUtils.findAccount(token).getUuid(), deliveryStatus);
        ListResponse response = new ListResponse(rawResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/sale_order/{order_item_uuid}")
    public ResponseEntity<PaidItemResponseM2> getSaleOrderItem(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("order_item_uuid") UUID orderItemUuid
    ) throws AlphanahBaseException {
        AccountUtils.merchantVerify(token);
        PaidItemResponseM2 response = business.getSaleOrderItem(AccountUtils.findAccount(token).getUuid(), orderItemUuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/sale_order/{order_item_uuid}")
    public ResponseEntity<PaidItemResponseM2> updateSaleOrderItem(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("order_item_uuid") UUID orderItemUuid
    ) throws AlphanahBaseException {
        AccountUtils.merchantVerify(token);
        PaidItemResponseM2 response = business.updateDeliveryStatus(AccountUtils.findAccount(token).getUuid(), orderItemUuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
