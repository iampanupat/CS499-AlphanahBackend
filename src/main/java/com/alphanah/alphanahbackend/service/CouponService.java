package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Coupon;
import com.alphanah.alphanahbackend.enumerate.CouponType;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.CouponException;
import com.alphanah.alphanahbackend.repository.CouponRepository;
import com.alphanah.alphanahbackend.utility.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CouponService {

    @Autowired
    private CouponRepository repository;

    public List<Coupon> findAllCoupons() {
        return (List<Coupon>) repository.findAll();
    }

    public Coupon findCoupon(String couponCode) throws AlphanahBaseException {
        if (Objects.isNull(couponCode))
            throw CouponException.cannotFindByNullCouponCode();

        Optional<Coupon> optional = repository.findById(couponCode);
        if (optional.isEmpty())
            throw CouponException.notFound();

        return optional.get();
    }

    public Coupon createCoupon(UUID creatorUuid, String couponCode, CouponType couponType, Long couponValue, Date couponStartDate, Date couponEndDate, Integer couponMaxUse) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw CouponException.cannotCreateWithNullCreatorUuid();

        if (Objects.isNull(couponCode))
            throw CouponException.cannotCreateWithNullCouponCode();

        if (Objects.isNull(couponType))
            throw CouponException.cannotCreateWithNullCouponType();

        if (Objects.isNull(couponValue))
            throw CouponException.cannotCreateWithNullCouponValue();

        if (Objects.isNull(couponStartDate))
            throw CouponException.cannotCreateWithNullCouponStartDate();

        if (repository.existsById(couponCode.toUpperCase()))
            throw CouponException.cannotCreateDuplicateCouponCode();

        if ((couponType.equals(CouponType.GIFT_CARD) || couponType.equals(CouponType.PERCENTAGE_DISCOUNT)) && couponValue <= 0)
            throw CouponException.cannotCreateWithNegativeOrZeroValue();

        if (couponType.equals(CouponType.GIFT_CARD) && couponValue > Env.COUPON_GIFT_CARD_MAX_VALUE)
            throw CouponException.cannotCreateWithValueExceedGiftCardMaxValue();

        if (couponType.equals(CouponType.PERCENTAGE_DISCOUNT) && couponValue > Env.COUPON_PERCENTAGE_DISCOUNT_MAX_VALUE)
            throw CouponException.cannotCreateWithValueExceedPercentageDiscountMaxValue();

        if (!Objects.isNull(couponEndDate) && couponStartDate.after(couponEndDate))
            throw CouponException.cannotCreateWithInvalidDate();

        if (!Objects.isNull(couponMaxUse) && couponMaxUse <= 0)
            throw CouponException.cannotCreateWithNegativeOrZeroCouponMaxUse();

        Coupon entity = new Coupon();
        switch (couponType) {
            case FREE_SHIPPING, GIFT_CARD, PERCENTAGE_DISCOUNT -> {
                entity.setCode(couponCode.toUpperCase());
                entity.setType(couponType);
                entity.setValue(couponValue);
                entity.setStartDate(couponStartDate);
                entity.setEndDate(couponEndDate);
                entity.setCreatorUuid(creatorUuid);
                entity.setUseCount(0);
                entity.setMaxUse(Objects.isNull(couponMaxUse) ? null : couponMaxUse);
            }
            default -> throw CouponException.cannotCreateWithUnsupportedCouponType();
        }
        return repository.save(entity);
    }

    public Coupon updateCouponUsage(String couponCode) throws AlphanahBaseException {
        if (Objects.isNull(couponCode))
            throw CouponException.cannotUpdateWithNullCouponCode();

        Coupon entity = this.findCoupon(couponCode);
        if (entity.isRunOut())
            throw CouponException.cannotUpdateWithRunOutCoupon();

        entity.setUseCount(entity.getUseCount() + 1);
        return repository.save(entity);
    }

    public void deleteCoupon(UUID creatorUuid, String couponCode) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw CouponException.cannotDeleteWithNullCreatorUuid();

        if (Objects.isNull(couponCode))
            throw CouponException.cannotDeleteWithNullCouponCode();

        Coupon entity = this.findCoupon(couponCode);
        if (!creatorUuid.equals(entity.getCreatorUuid()))
            throw CouponException.cannotDeleteNotOwned();

        repository.delete(entity);
    }

}