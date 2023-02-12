package com.alphanah.alphanahbackend.exception;

public class OrderItemException extends AlphanahBaseException {

    public OrderItemException(String message) {
        super("order.item." + message);
    }

    public static OrderException createOrUpdateWithNullCreatorUuid() {
        return new OrderException("create.or.update.with.null.creator.uuid");
    }

    public static OrderException createOrUpdateWithNullProductUuid() {
        return new OrderException("create.or.update.with.null.product.uuid");
    }

    public static OrderException createOrUpdateWithNullProductOptionUuid() {
        return new OrderException("create.or.update.with.null.product.option.uuid");
    }

    public static OrderException createOrUpdateWithNegativeQuantity() {
        return new OrderException("create.or.update.with.negative.quantity");
    }

    public static OrderException createOrUpdateWithMaxValueQuantity() {
        return new OrderException("create.or.update.with.max.value.quantity");
    }

    public static OrderException createOrUpdateWithNullOrderObject() {
        return new OrderException("create.or.update.with.null.order.object");
    }

    public static OrderException createOrUpdateWithNullProductOptionObject() {
        return new OrderException("create.or.update.with.null.product.option.object");
    }

    public static OrderException createOrUpdateWithOverStockQuantity() {
        return new OrderException("create.or.update.with.over.stock.quantity");
    }

    public static OrderException createOrUpdateWithNullOrderItemObject() {
        return new OrderException("create.or.update.with.null.order.item.object");
    }

    public static OrderException deleteWithNullCreatorUuid() {
        return new OrderException("delete.with.null.creator.uuid");
    }

    public static OrderException deleteWithNullProductUuid() {
        return new OrderException("delete.with.null.product.uuid");
    }

    public static OrderException deleteWithNullProductOptionUuid() {
        return new OrderException("delete.with.null.product.option.uuid");
    }

    public static OrderException deleteWithNullOrderObject() {
        return new OrderException("delete.with.null.order.object");
    }

    public static OrderException deleteWithNullProductOptionObject() {
        return new OrderException("delete.with.null.product.option.object");
    }

    public static OrderException deleteNullObject() {
        return new OrderException("delete.null.object");
    }

    public static OrderException findAllPaidOrderItemWithNullProductCreatorUuid() {
        return new OrderException("find.all.paid.order.item.with.null.product.creator.uuid");
    }

    public static OrderException findPaidOrderItemWithNullProductCreatorUuid() {
        return new OrderException("find.paid.order.item.with.null.product.creator.uuid");
    }

    public static OrderException findPaidOrderItemWithNullOrderItemUuid() {
        return new OrderException("find.paid.order.item.with.null.order.item.uuid");
    }

    public static OrderItemException findNullOrderItemObject() {
        return new OrderItemException("find.null.order.item.object");
    }

    public static OrderItemException findNotOwned() {
        return new OrderItemException("find.not.owned");
    }

    public static OrderItemException updatePaidOrderItemWithNullProductCreatorUuid() {
        return new OrderItemException("update.paid.order.item.with.null.product.creator.uuid");
    }

    public static OrderItemException updatePaidOrderItemWithNullOrderItemUuid() {
        return new OrderItemException("update.paid.order.item.with.null.order.item.uuid");
    }

}
