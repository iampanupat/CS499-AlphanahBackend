package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.OrderItemBusiness;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.ListResponse;
import com.alphanah.alphanahbackend.model.order_item.CartItemResponseM2;
import com.alphanah.alphanahbackend.model.order_item.OrderItemRequest;
import com.alphanah.alphanahbackend.model.order_item.PaidItemResponseM2;
import com.alphanah.alphanahbackend.service.OrderItemService;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderItemApi {

    @Autowired
    private OrderItemBusiness business;

    @PostMapping("/cart/{product_uuid}/option/{product_option_uuid}")
    public ResponseEntity<CartItemResponseM2> createOrUpdateCartItem(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("product_uuid") UUID productUuid,
            @PathVariable("product_option_uuid") UUID optionUuid,
            @RequestBody OrderItemRequest request
    ) throws AlphanahBaseException {
        CartItemResponseM2 response = business.createOrUpdateCartItem(AccountUtils.findAccount(token).getUuid(), productUuid, optionUuid, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/cart/{product_uuid}/option/{product_option_uuid}")
    public ResponseEntity<Void> deleteCartItem(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("product_uuid") UUID productUuid,
            @PathVariable("product_option_uuid") UUID optionUuid
    ) throws AlphanahBaseException {
        business.deleteCartItem(AccountUtils.findAccount(token).getUuid(), productUuid, optionUuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sale_order")
    public ResponseEntity<ListResponse> getAllSaleOrderItem(@RequestHeader(value = "Authorization") String token) throws AlphanahBaseException {
        List<PaidItemResponseM2> rawResponse = business.getAllSaleOrderItem(AccountUtils.findAccount(token).getUuid());
        ListResponse response = new ListResponse(rawResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/sale_order/{order_item_uuid}")
    public ResponseEntity<PaidItemResponseM2> getSaleOrderItem(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("order_item_uuid") UUID orderItemUuid
    ) throws AlphanahBaseException {
        PaidItemResponseM2 response = business.getSaleOrderItem(AccountUtils.findAccount(token).getUuid(), orderItemUuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/sale_order/{order_item_uuid}")
    public ResponseEntity<PaidItemResponseM2> updateSaleOrderItem(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("order_item_uuid") UUID orderItemUuid
    ) throws AlphanahBaseException {
        PaidItemResponseM2 response = business.updateDeliveryStatus(AccountUtils.findAccount(token).getUuid(), orderItemUuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
