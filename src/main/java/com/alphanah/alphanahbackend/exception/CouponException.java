package com.alphanah.alphanahbackend.exception;

public class CouponException extends AlphanahBaseException {

    public CouponException(String message) {
        super("coupon." + message);
    }

    public static CouponException cannotFindByNullCouponUuid() {
        return new CouponException("cannot.find.by.null.coupon.uuid");
    }

    public static CouponException notFound() {
        return new CouponException("not.found");
    }

    public static CouponException cannotCreateWithNullCreatorUuid() {
        return new CouponException("cannot.create.with.null.creator.uuid");
    }

    public static CouponException cannotCreateWithNullCouponType() {
        return new CouponException("cannot.create.with.null.coupon.type");
    }

    public static CouponException cannotCreateWithNegativeOrZeroValue() {
        return new CouponException("cannot.create.with.negative.or.zero.value");
    }

    public static CouponException cannotCreateWithOverMaxCouponGiftCardValue() {
        return new CouponException("cannot.create.with.over.max.coupon.gift.card.value");
    }

    public static CouponException cannotCreateWithOverMaxCouponPercentageDiscountValue() {
        return new CouponException("cannot.create.with.over.max.coupon.percentage.discount.value");
    }

    public static CouponException cannotCreateWithUnsupportedCouponType() {
        return new CouponException("cannot.create.with.unsupported.coupon.type");
    }

    public static CouponException cannotUpdateWithNullCouponUuid() {
        return new CouponException("cannot.update.with.null.coupon.uuid");
    }

    public static CouponException cannotDeleteWithNullCreatorUuid() {
        return new CouponException("cannot.delete.with.null.creator.uuid");
    }

    public static CouponException cannotDeleteWithNullCouponUuid() {
        return new CouponException("cannot.delete.with.null.coupon.uuid");
    }

    public static CouponException cannotDeleteNotOwned() {
        return new CouponException("cannot.delete.not.owned");
    }

    public static CouponException cannotDeleteUsed() {
        return new CouponException("cannot.delete.used");
    }

}
