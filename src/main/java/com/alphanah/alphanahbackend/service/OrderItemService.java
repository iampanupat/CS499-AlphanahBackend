package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Order;
import com.alphanah.alphanahbackend.entity.OrderItem;
import com.alphanah.alphanahbackend.entity.ProductOption;
import com.alphanah.alphanahbackend.enumerate.DeliveryStatus;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.OrderException;
import com.alphanah.alphanahbackend.exception.OrderItemException;
import com.alphanah.alphanahbackend.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository repository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductOptionService optionService;

    private final int ORDER_ITEM_QUANTITY_MAX_VALUE = 1000000;

    public OrderItem createOrUpdateCartItem(UUID creatorUuid, UUID productUuid, UUID optionUuid, int quantity) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderItemException.createOrUpdateWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw OrderItemException.createOrUpdateWithNullProductUuid();

        if (Objects.isNull(optionUuid))
            throw OrderItemException.createOrUpdateWithNullProductOptionUuid();

        if (quantity < 0)
            throw OrderItemException.createOrUpdateWithNegativeQuantity();

        if (quantity > ORDER_ITEM_QUANTITY_MAX_VALUE)
            throw OrderItemException.createOrUpdateWithMaxValueQuantity();

        Order cart;
        try {
            cart = orderService.getOrCreateCart(creatorUuid);
        } catch (AlphanahBaseException exception) {
            throw OrderItemException.createOrUpdateWithNullOrderObject();
        }

        ProductOption option;
        try {
            option = optionService.get(productUuid, optionUuid);
        } catch (AlphanahBaseException exception) {
            throw OrderItemException.createOrUpdateWithNullProductOptionObject();
        }

        if (quantity > option.getQuantity())
            throw OrderItemException.createOrUpdateWithOverStockQuantity();

        OrderItem entity;
        if (!repository.existsByOrderAndProductOption(cart, option)) {
            entity = new OrderItem();
            entity.setPrice(-1.0);
            entity.setOrder(cart);
            entity.setProductOption(option);
            entity.setDeliveryStatus(DeliveryStatus.CART_ITEM.toString());
        } else {
            Optional<OrderItem> optional = repository.findByOrderAndProductOption(cart, option);
            if (optional.isEmpty())
                throw OrderItemException.createOrUpdateWithNullOrderItemObject();
            entity = optional.get();
        }
        entity.setQuantity(quantity);
        return repository.save(entity);
    }

    public void deleteCartItem(UUID creatorUuid, UUID productUuid, UUID optionUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderItemException.deleteWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw OrderItemException.deleteWithNullProductUuid();

        if (Objects.isNull(optionUuid))
            throw OrderItemException.deleteWithNullProductOptionUuid();

        Order cart;
        try {
            cart = orderService.getOrCreateCart(creatorUuid);
        } catch (AlphanahBaseException exception) {
            throw OrderItemException.deleteWithNullOrderObject();
        }

        ProductOption option;
        try {
            option = optionService.get(productUuid, optionUuid);
        } catch (AlphanahBaseException exception) {
            throw OrderItemException.deleteWithNullProductOptionObject();
        }

        Optional<OrderItem> optional = repository.findByOrderAndProductOption(cart, option);
        if (optional.isEmpty())
            throw OrderItemException.deleteNullObject();

        OrderItem entity = optional.get();
        repository.delete(entity);
    }

    public List<OrderItem> findAllPaidOrderItem(UUID productCreatorUuid) throws AlphanahBaseException {
        if (Objects.isNull(productCreatorUuid))
            throw OrderItemException.findAllPaidOrderItemWithNullProductCreatorUuid();

        List<OrderItem> saleOrderItemList = new ArrayList<>();
        List<OrderItem> orderItemList = (List<OrderItem>) repository.findAll();
        for (OrderItem saleOrderItem: orderItemList) {
            if (productCreatorUuid.equals(UUID.fromString(saleOrderItem.getProductOption().getProduct().getCreatorUuid())))
                saleOrderItemList.add(saleOrderItem);
        }

        Collections.sort(saleOrderItemList);
        return saleOrderItemList;
    }

    public OrderItem findPaidOrderItem(UUID productCreatorUuid, UUID orderItemUuid) throws AlphanahBaseException {
        if (Objects.isNull(productCreatorUuid))
            throw OrderItemException.findPaidOrderItemWithNullProductCreatorUuid();

        if (Objects.isNull(orderItemUuid))
            throw OrderItemException.findPaidOrderItemWithNullOrderItemUuid();

        Optional<OrderItem> optional = repository.findById(orderItemUuid.toString());
        if (optional.isEmpty())
            throw OrderItemException.findNullOrderItemObject();

        OrderItem orderItem = optional.get();
        if (!productCreatorUuid.equals(UUID.fromString(orderItem.getProductOption().getProduct().getCreatorUuid())))
            throw OrderItemException.findNotOwned();

        return orderItem;
    }

    public OrderItem updateDeliveryStatus(UUID productCreatorUuid, UUID orderItemUuid) throws AlphanahBaseException {
        if (Objects.isNull(productCreatorUuid))
            throw OrderItemException.updatePaidOrderItemWithNullProductCreatorUuid();

        if (Objects.isNull(orderItemUuid))
            throw OrderItemException.updatePaidOrderItemWithNullOrderItemUuid();

        OrderItem entity = this.findPaidOrderItem(productCreatorUuid, orderItemUuid);
        DeliveryStatus status = DeliveryStatus.valueOf(entity.getDeliveryStatus());
        switch (status) {
            case PENDING -> entity.setDeliveryStatus(DeliveryStatus.SHIPPED.toString());
            case SHIPPED -> entity.setDeliveryStatus(DeliveryStatus.DELIVERED.toString());
        }

        return repository.save(entity);
    }

}