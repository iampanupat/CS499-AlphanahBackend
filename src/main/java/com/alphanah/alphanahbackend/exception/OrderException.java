package com.alphanah.alphanahbackend.exception;

public class OrderException extends AlphanahBaseException {

    public OrderException(String message) {
        super("order." + message);
    }

    public static OrderException getAllWithNullCreatorUuid() {
        return new OrderException("get.all.with.null.creator.uuid");
    }

    public static OrderException getWithNullUuid() {
        return new OrderException("get.with.null.uuid");
    }

    public static OrderException getNullObject() {
        return new OrderException("get.null.object");
    }

}
