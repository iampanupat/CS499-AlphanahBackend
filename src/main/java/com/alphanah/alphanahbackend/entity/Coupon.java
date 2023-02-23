package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.enumerate.CouponType;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.coupon.CouponResponseM1;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import com.alphanah.alphanahbackend.utility.DateUtils;
import com.alphanah.alphanahbackend.utility.Env;
import jakarta.persistence.*;
import lombok.Data;
import org.joda.time.DateTimeZone;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity(name = "coupons")
public class Coupon {

    @Id
    @Column(name = "coupon_code", nullable = false, updatable = false, length = 120)
    private String code;

    @Column(name = "coupon_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private CouponType type;

    @Column(name = "coupon_value", nullable = false, updatable = false)
    private Long value = -1L;

    @Column(name = "coupon_start_date", nullable = false, updatable = false)
    private Date startDate;

    @Column(name = "coupon_end_date", updatable = false)
    private Date endDate;

    @Column(name = "coupon_creator_uuid", nullable = false, updatable = false)
    private UUID creatorUuid;

    @Column(name = "coupon_use_count", nullable = false)
    private Integer useCount = 0;

    @Column(name = "coupon_max_use", updatable = false)
    private Integer maxUse = null;

    @Column(name = "coupon_soft_delete", nullable = false)
    private Boolean softDelete = false;

    public boolean isAvailable() {
        return this.isStarted() && !this.isExpired() && !this.isRunOut() && !this.isDeleted();
    }

    public boolean isStarted() {
        return this.startDate.before(new Date());
    }

    public boolean isExpired() {
        if (Objects.isNull(endDate))
            return false;
        return this.endDate.before(new Date());
    }

    public boolean isRunOut() {
        if (Objects.isNull(maxUse))
            return false;
        return useCount >= maxUse;
    }

    public boolean isDeleted() {
        return softDelete;
    }

    public CouponResponseM1 toCouponResponseM1() throws AlphanahBaseException {
        return this.toCouponResponseM1(null);
    }

    private CouponResponseM1 toCouponResponseM1(CouponResponseM1 response) throws AlphanahBaseException {
        if (response == null)
            response = new CouponResponseM1();

        response.setSoftDelete(softDelete);
        response.setCouponCode(code);
        response.setType(type);
        response.setValue(value);
        response.setStartDate(Objects.isNull(startDate) ? null : DateUtils.timeZoneConverter(startDate, Env.bangkokZone));
        response.setEndDate(Objects.isNull(endDate) ? null : DateUtils.timeZoneConverter(endDate, Env.bangkokZone));
        response.setCreator(AccountUtils.findAccount(creatorUuid).toAccountResponseM1());
        response.setMaxUse(maxUse);
        response.setUseCount(useCount);
        return response;
    }

}