package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.OrderItem;
import com.alphanah.alphanahbackend.enumerate.OrderType;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.order_item.CartItemResponseM2;
import com.alphanah.alphanahbackend.model.order_item.OrderItemRequest;
import com.alphanah.alphanahbackend.model.order_item.PaidItemResponseM2;
import com.alphanah.alphanahbackend.service.OrderItemService;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderItemBusiness {

    @Autowired
    private OrderItemService service;

    public CartItemResponseM2 createOrUpdateCartItem(UUID accountUuid, UUID productUuid, UUID optionUuid, OrderItemRequest request) throws AlphanahBaseException {
        OrderItem response = service.createOrUpdateCartItem(accountUuid, productUuid, optionUuid, request.getQuantity());
        return response.toCartItemResponseM2(null);
    }

    public void deleteCartItem(UUID accountUuid, UUID productUuid, UUID optionUuid) throws AlphanahBaseException {
        service.deleteCartItem(accountUuid, productUuid, optionUuid);
    }

    public List<PaidItemResponseM2> getAllSaleOrderItem(UUID merchantUuid) throws AlphanahBaseException {
        List<PaidItemResponseM2> responses = new ArrayList<>();
        List<OrderItem> saleOrderItemList = service.findAllPaidOrderItem(merchantUuid);
        for (OrderItem saleOrderItem: saleOrderItemList) {
            if (saleOrderItem.getOrder().getType().equals(OrderType.CART.toString()))
                continue;
            responses.add(saleOrderItem.toPaidItemResponseM2(null));
        }
        return responses;
    }

    public PaidItemResponseM2 getSaleOrderItem(UUID merchantUuid, UUID orderItemUuid) throws AlphanahBaseException {
        OrderItem response = service.findPaidOrderItem(merchantUuid, orderItemUuid);
        return response.toPaidItemResponseM2(null);
    }

    public PaidItemResponseM2 updateDeliveryStatus(UUID merchantUuid, UUID orderItemUuid) throws AlphanahBaseException {
        OrderItem response = service.updateDeliveryStatus(merchantUuid, orderItemUuid);
        return response.toPaidItemResponseM2(null);
    }

}
