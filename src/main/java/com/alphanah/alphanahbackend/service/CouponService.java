package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Coupon;
import com.alphanah.alphanahbackend.enumerate.CouponType;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.CouponException;
import com.alphanah.alphanahbackend.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CouponService {

    @Autowired
    private CouponRepository repository;

    private final int COUPON_PERCENTAGE_DISCOUNT_MAX_VALUE = 100;
    private final int COUPON_GIFT_CARD_MAX_VALUE = 1000000;

    public List<Coupon> findAllCoupons() {
        return (List<Coupon>) repository.findAll();
    }

    public Coupon findCoupon(UUID couponUuid) throws AlphanahBaseException {
        if (Objects.isNull(couponUuid))
            throw CouponException.cannotFindByNullCouponUuid();

        Optional<Coupon> optional = repository.findById(couponUuid);
        if (optional.isEmpty())
            throw CouponException.notFound();

        return optional.get();
    }

    public Coupon createCoupon(UUID creatorUuid, CouponType type, long value, Date expiredDate) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw CouponException.cannotCreateWithNullCreatorUuid();

        if (Objects.isNull(type))
            throw CouponException.cannotCreateWithNullCouponType();

        if ((type.equals(CouponType.GIFT_CARD) || type.equals(CouponType.PERCENTAGE_DISCOUNT)) && value <= 0)
            throw CouponException.cannotCreateWithNegativeOrZeroValue();

        if (type.equals(CouponType.GIFT_CARD) && value > COUPON_GIFT_CARD_MAX_VALUE)
            throw CouponException.cannotCreateWithOverMaxCouponGiftCardValue();

        if (type.equals(CouponType.PERCENTAGE_DISCOUNT) && value > COUPON_PERCENTAGE_DISCOUNT_MAX_VALUE)
            throw CouponException.cannotCreateWithOverMaxCouponPercentageDiscountValue();

        Coupon entity = new Coupon();
        switch (type) {
            case FREE_SHIPPING, GIFT_CARD, PERCENTAGE_DISCOUNT -> {
                entity.setType(type);
                entity.setValue(value);
                entity.setCreatorUuid(creatorUuid);
                entity.setExpiredDate(expiredDate);
            }
            default -> throw CouponException.cannotCreateWithUnsupportedCouponType();
        }
        return repository.save(entity);
    }

    public Coupon updateCouponUsageStatus(UUID couponUuid) throws AlphanahBaseException {
        if (Objects.isNull(couponUuid))
            throw CouponException.cannotUpdateWithNullCouponUuid();

        Coupon entity = this.findCoupon(couponUuid);
        entity.setUsageStatus(true);
        return repository.save(entity);
    }

    public void deleteCoupon(UUID creatorUuid, UUID couponUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw CouponException.cannotDeleteWithNullCreatorUuid();

        if (Objects.isNull(couponUuid))
            throw CouponException.cannotDeleteWithNullCouponUuid();

        Coupon entity = this.findCoupon(couponUuid);
        if (!creatorUuid.equals(entity.getCreatorUuid()))
            throw CouponException.cannotDeleteNotOwned();

        if (entity.getUsageStatus())
            throw CouponException.cannotDeleteUsed();

        repository.delete(entity);
    }

}