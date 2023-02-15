package com.alphanah.alphanahbackend.exception;

public class OrderItemException extends AlphanahBaseException {

    public OrderItemException(String message) {
        super("order.item." + message);
    }

    public static OrderItemException cannotUpdateWithNullCreatorUuid() {
        return new OrderItemException("cannot.update.with.null.creator.uuid");
    }

    public static OrderItemException cannotUpdateWithNullProductUuid() {
        return new OrderItemException("cannot.update.with.null.product.uuid");
    }

    public static OrderItemException cannotUpdateWithNullProductOptionUuid() {
        return new OrderItemException("cannot.update.with.null.product.option.uuid");
    }

    public static OrderItemException cannotUpdateWithNullQuantity() {
        return new OrderItemException("cannot.update.with.null.quantity");
    }

    public static OrderItemException cannotUpdateWithNegativeOrZeroQuantity() {
        return new OrderItemException("cannot.update.with.negative.or.zero.quantity");
    }

    public static OrderItemException cannotUpdateWithQuantityExceedMaxValue() {
        return new OrderItemException("cannot.update.with.quantity.exceed.max.value");
    }

    public static OrderItemException cannotUpdateWithQuantityExceedStock() {
        return new OrderItemException("cannot.update.with.quantity.exceed.stock");
    }

    public static OrderItemException notFound() {
        return new OrderItemException("not.found");
    }

    public static OrderItemException cannotDeleteWithNullCreatorUuid() {
        return new OrderItemException("cannot.delete.with.null.creator.uuid");
    }

    public static OrderItemException cannotDeleteWithNullProductUuid() {
        return new OrderItemException("cannot.delete.with.null.product.uuid");
    }

    public static OrderItemException cannotDeleteWithNullProductOptionUuid() {
        return new OrderItemException("cannot.delete.with.null.product.option.uuid");
    }

    public static OrderItemException cannotFindWithNullCreatorUuid() {
        return new OrderItemException("cannot.find.with.null.creator.uuid");
    }

    public static OrderItemException cannotFindWithNullOrderItemUuid() {
        return new OrderItemException("cannot.find.with.null.order.item.uuid");
    }

    public static OrderItemException cannotUpdateWithNullOrderItemUuid() {
        return new OrderItemException("cannot.update.with.null.order.item.uuid");
    }

}
