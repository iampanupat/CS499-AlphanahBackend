package com.alphanah.alphanahbackend.exception;

public class OrderException extends AlphanahBaseException {

    public OrderException(String message) {
        super("order." + message);
    }

    public static OrderException checkoutFailure() {
        return new OrderException("checkout.failure");
    }

    public static OrderException cannotApplyAlreadyUsedCoupon() {
        return new OrderException("cannot.apply.already.used.coupon");
    }

    public static OrderException cannotFindWithNullCreatorUuid() {
        return new OrderException("cannot.find.with.null.creator.uuid");
    }

    public static OrderException cannotFindWithNullOrderUuid() {
        return new OrderException("cannot.find.with.null.order.uuid");
    }

    public static OrderException notFound() {
        return new OrderException("not.found");
    }

    public static OrderException cannotCreateWithNullCreatorUuid() {
        return new OrderException("cannot.create.with.null.creator.uuid");
    }

    public static OrderException cannotCreateDuplicateCart() {
        return new OrderException("cannot.create.duplicate.cart");
    }

    public static OrderException cannotUpdateWithNullCreatorUuid() {
        return new OrderException("cannot.update.with.null.creator.uuid");
    }

    public static OrderException cannotUpdateWithNullPayType() {
        return new OrderException("cannot.update.with.null.pay.type");
    }

    public static OrderException cannotUpdateWithNullFirstname() {
        return new OrderException("cannot.update.with.null.firstname");
    }

    public static OrderException cannotUpdateWithNullLastname() {
        return new OrderException("cannot.update.with.null.lastname");
    }

    public static OrderException cannotUpdateWithNullPhone() {
        return new OrderException("cannot.update.with.null.phone");
    }

    public static OrderException cannotUpdateWithNullAddress() {
        return new OrderException("cannot.update.with.null.address");
    }

    public static OrderException cannotUpdateWithEmptyFirstname() {
        return new OrderException("cannot.update.with.empty.firstname");
    }

    public static OrderException cannotUpdateWithEmptyLastname() {
        return new OrderException("cannot.update.with.empty.lastname");
    }

    public static OrderException cannotUpdateWithEmptyPhone() {
        return new OrderException("cannot.update.with.empty.phone");
    }

    public static OrderException cannotUpdateWithEmptyAddress() {
        return new OrderException("cannot.update.with.empty.address");
    }

    public static OrderException cannotUpdateWithFirstnameExceedMaxLength() {
        return new OrderException("cannot.update.with.firstname.exceed.max.length");
    }

    public static OrderException cannotUpdateWithLastnameExceedMaxLength() {
        return new OrderException("cannot.update.with.lastname.exceed.max.length");
    }

    public static OrderException cannotUpdateWithPhoneExceedMaxLength() {
        return new OrderException("cannot.update.with.phone.exceed.max.length");
    }

    public static OrderException cannotUpdateWithAddressExceedMaxLength() {
        return new OrderException("cannot.update.with.address.exceed.max.length");
    }

    public static OrderException cannotUpdateWithNullCartUuid() {
        return new OrderException("cannot.update.with.null.cart.uuid");
    }

    public static OrderException cannotUpdateWithEmptyCart() {
        return new OrderException("cannot.update.with.empty.cart");
    }

    public static OrderException cannotUpdateWithQuantityExceedStock() {
        return new OrderException("cannot.update.with.quantity.exceed.stock");
    }

}
