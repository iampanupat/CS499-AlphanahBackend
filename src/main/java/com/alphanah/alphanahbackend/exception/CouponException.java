package com.alphanah.alphanahbackend.exception;

public class CouponException extends AlphanahBaseException {

    public CouponException(String message) {
        super("coupon." + message);
    }

    public static CouponException cannotFindByNullCouponCode() {
        return new CouponException("cannot.find.by.null.coupon.code");
    }

    public static CouponException notFound() {
        return new CouponException("not.found");
    }

    public static CouponException cannotCreateWithNullCreatorUuid() {
        return new CouponException("cannot.create.with.null.creator.uuid");
    }

    public static CouponException cannotCreateWithNullCouponCode() {
        return new CouponException("cannot.create.with.null.coupon.code");
    }

    public static CouponException cannotCreateWithNullCouponType() {
        return new CouponException("cannot.create.with.null.coupon.type");
    }

    public static CouponException cannotCreateWithNullCouponValue() {
        return new CouponException("cannot.create.with.null.coupon.value");
    }

    public static CouponException cannotCreateWithNullCouponStartDate() {
        return new CouponException("cannot.create.with.null.coupon.start.date");
    }

    public static CouponException cannotCreateWithNullCouponMaxUse() {
        return new CouponException("cannot.create.with.null.coupon.max.use");
    }

    public static CouponException cannotCreateDuplicateCouponCode() {
        return new CouponException("cannot.create.duplicate.coupon.code");
    }

    public static CouponException cannotCreateWithNegativeOrZeroValue() {
        return new CouponException("cannot.create.with.negative.or.zero.value");
    }

    public static CouponException cannotCreateWithValueExceedGiftCardMaxValue() {
        return new CouponException("cannot.create.with.value.exceed.gift.card.max.value");
    }

    public static CouponException cannotCreateWithValueExceedPercentageDiscountMaxValue() {
        return new CouponException("cannot.create.with.value.exceed.percentage.discount.max.value");
    }

    public static CouponException cannotCreateWithInvalidDate() {
        return new CouponException("cannot.create.with.invalid.date");
    }

    public static CouponException cannotCreateWithNegativeOrZeroCouponMaxUse() {
        return new CouponException("cannot.create.with.negative.or.zero.coupon.max.use");
    }

    public static CouponException cannotCreateWithUnsupportedCouponType() {
        return new CouponException("cannot.create.with.unsupported.coupon.type");
    }

    public static CouponException cannotUpdateWithNullCouponCode() {
        return new CouponException("cannot.update.with.null.coupon.code");
    }

    public static CouponException cannotUpdateWithRunOutCoupon() {
        return new CouponException("cannot.update.with.run.out.coupon");
    }

    public static CouponException cannotDeleteWithNullCreatorUuid() {
        return new CouponException("cannot.delete.with.null.creator.uuid");
    }

    public static CouponException cannotDeleteWithNullCouponCode() {
        return new CouponException("cannot.delete.with.null.coupon.code");
    }

    public static CouponException cannotDeleteNotOwned() {
        return new CouponException("cannot.delete.not.owned");
    }

}
