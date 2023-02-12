package com.alphanah.alphanahbackend.exception;

public class OrderException extends AlphanahBaseException {

    public OrderException(String message) {
        super("order." + message);
    }

    public static OrderException checkoutFailure() {
        return new OrderException("checkout.failure");
    }

    public static OrderException getAllWithNullCreatorUuid() {
        return new OrderException("get.all.with.null.creator.uuid");
    }

    public static OrderException getWithNullCreatorUuid() {
        return new OrderException("get.with.null.creator.uuid");
    }

    public static OrderException getWithNullUuid() {
        return new OrderException("get.with.null.uuid");
    }

    public static OrderException getNullObject() {
        return new OrderException("get.null.object");
    }

    public static OrderException getNotPaidOrder() {
        return new OrderException("get.not.paid.order");
    }

    public static OrderException getNotCartOrder() {
        return new OrderException("get.not.cart.order");
    }

    public static OrderException createWithNullCreatorUuid() {
        return new OrderException("create.with.null.creator.uuid");
    }

    public static OrderException createDuplicateCart() {
        return new OrderException("create.duplicate.cart");
    }

    public static OrderException createWithNullType() {
        return new OrderException("create.with.null.type");
    }


    public static OrderException updateWithNullCreatorUuid() {
        return new OrderException("update.with.null.creator.uuid");
    }

    public static OrderException updateWithNullFirstname() {
        return new OrderException("update.with.null.firstname");
    }

    public static OrderException updateWithNullLastname() {
        return new OrderException("update.with.null.lastname");
    }

    public static OrderException updateWithNullPhone() {
        return new OrderException("update.with.null.phone");
    }

    public static OrderException updateWithNullAddress() {
        return new OrderException("update.with.null.address");
    }

    public static OrderException updateWithEmptyFirstname() {
        return new OrderException("update.with.empty.firstname");
    }

    public static OrderException updateWithEmptyLastname() {
        return new OrderException("update.with.empty.lastname");
    }

    public static OrderException updateWithEmptyPhone() {
        return new OrderException("update.with.empty.phone");
    }

    public static OrderException updateWithEmptyAddress() {
        return new OrderException("update.with.empty.address");
    }

    public static OrderException updateWithMaxLengthFirstname() {
        return new OrderException("update.with.max.length.firstname");
    }

    public static OrderException updateWithMaxLengthLastname() {
        return new OrderException("update.with.max.length.lastname");
    }

    public static OrderException updateWithMaxLengthPhone() {
        return new OrderException("update.with.max.length.phone");
    }

    public static OrderException updateWithMaxLengthAddress() {
        return new OrderException("update.with.max.length.address");
    }

    public static OrderException updateCartToPaidWithOverStockQuantity() {
        return new OrderException("update.cart.to.paid.with.over.stock.quantity");
    }

    public static OrderException updateWithNullCartUuid() {
        return new OrderException("update.with.null.cart.uuid");
    }

    public static OrderException updateWithEmptyCart() {
        return new OrderException("update.with.empty.cart");
    }

}
