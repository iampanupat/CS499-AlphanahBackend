package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Order;
import com.alphanah.alphanahbackend.entity.OrderItem;
import com.alphanah.alphanahbackend.entity.ProductOption;
import com.alphanah.alphanahbackend.enumerate.DeliveryStatus;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.OrderException;
import com.alphanah.alphanahbackend.exception.OrderItemException;
import com.alphanah.alphanahbackend.repository.OrderItemRepository;
import com.alphanah.alphanahbackend.utility.Env;
import org.aspectj.weaver.ast.Or;
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

    public Order addOrCreateCartItem(UUID creatorUuid, UUID productUuid, UUID optionUuid, Integer quantity) throws AlphanahBaseException {
        Order cart = orderService.findOrCreateCart(creatorUuid);
        List<OrderItem> orderItems = cart.getOrderItems();
        for (OrderItem orderItem: orderItems) {
            if (orderItem.getProductOption().getUuid().equals(optionUuid)) {
                quantity += orderItem.getQuantity();
                break;
            }
        }
        return this.updateOrCreateCartItem(creatorUuid, productUuid, optionUuid, quantity);
    }

    public Order updateOrCreateCartItem(UUID creatorUuid, UUID productUuid, UUID optionUuid, Integer quantity) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderItemException.cannotUpdateWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw OrderItemException.cannotUpdateWithNullProductUuid();

        if (Objects.isNull(optionUuid))
            throw OrderItemException.cannotUpdateWithNullProductOptionUuid();

        if (Objects.isNull(quantity))
            throw OrderItemException.cannotUpdateWithNullQuantity();

        if (quantity <= 0)
            throw OrderItemException.cannotUpdateWithNegativeOrZeroQuantity();

        if (quantity > Env.PRODUCT_QUANTITY_MAX_VALUE)
            throw OrderItemException.cannotUpdateWithQuantityExceedMaxValue();

        ProductOption option = optionService.findProductOption(productUuid, optionUuid);
        if (quantity > option.getQuantity())
            throw OrderItemException.cannotUpdateWithQuantityExceedStock();

        OrderItem entity;
        Order cart = orderService.findOrCreateCart(creatorUuid);
        if (!repository.existsByOrderAndProductOption(cart, option)) {
            entity = new OrderItem();
            entity.setPrice(-1.0);
            entity.setOrder(cart);
            entity.setProductOption(option);
            entity.setDeliveryStatus(DeliveryStatus.CART_ITEM);
        } else {
            Optional<OrderItem> optional = repository.findByOrderAndProductOption(cart, option);
            if (optional.isEmpty())
                throw OrderItemException.notFound();
            entity = optional.get();
        }
        entity.setQuantity(quantity);
        return repository.save(entity).getOrder();
    }

    public Order deleteCartItem(UUID creatorUuid, UUID productUuid, UUID optionUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderItemException.cannotDeleteWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw OrderItemException.cannotDeleteWithNullProductUuid();

        if (Objects.isNull(optionUuid))
            throw OrderItemException.cannotDeleteWithNullProductOptionUuid();

        Order cart = orderService.findOrCreateCart(creatorUuid);
        ProductOption option = optionService.findProductOption(productUuid, optionUuid);

        Optional<OrderItem> optional = repository.findByOrderAndProductOption(cart, option);
        if (optional.isEmpty())
            throw OrderItemException.notFound();

        OrderItem entity = optional.get();
        repository.delete(entity);
        return orderService.findOrCreateCart(creatorUuid);
    }

    public List<OrderItem> findAllPaidOrderItem(UUID creatorUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderItemException.cannotFindWithNullCreatorUuid();

        List<OrderItem> saleOrderItemList = new ArrayList<>();
        List<OrderItem> orderItemList = (List<OrderItem>) repository.findAll();
        for (OrderItem saleOrderItem: orderItemList) {
            if (!saleOrderItem.getOrder().isPaid())
                continue;
            if (!creatorUuid.equals(saleOrderItem.getProductOption().getProduct().getCreatorUuid()))
                continue;
            saleOrderItemList.add(saleOrderItem);
        }
        Collections.sort(saleOrderItemList);
        return saleOrderItemList;
    }

    public OrderItem findPaidOrderItem(UUID creatorUuid, UUID orderItemUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderItemException.cannotFindWithNullCreatorUuid();

        if (Objects.isNull(orderItemUuid))
            throw OrderItemException.cannotFindWithNullOrderItemUuid();

        Optional<OrderItem> optional = repository.findById(orderItemUuid);
        if (optional.isEmpty())
            throw OrderItemException.notFound();

        OrderItem orderItem = optional.get();
        if (!orderItem.getOrder().isPaid())
            throw OrderItemException.notFound();

        if (!creatorUuid.equals(orderItem.getProductOption().getProduct().getCreatorUuid()))
            throw OrderItemException.notFound();

        return orderItem;
    }

    public OrderItem updateDeliveryStatus(UUID creatorUuid, UUID orderItemUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderItemException.cannotUpdateWithNullCreatorUuid();

        if (Objects.isNull(orderItemUuid))
            throw OrderItemException.cannotUpdateWithNullOrderItemUuid();

        OrderItem entity = this.findPaidOrderItem(creatorUuid, orderItemUuid);
        DeliveryStatus status = entity.getDeliveryStatus();
        switch (status) {
            case PENDING -> entity.setDeliveryStatus(DeliveryStatus.SHIPPED);
            case SHIPPED -> entity.setDeliveryStatus(DeliveryStatus.DELIVERED);
        }

        return repository.save(entity);
    }

}