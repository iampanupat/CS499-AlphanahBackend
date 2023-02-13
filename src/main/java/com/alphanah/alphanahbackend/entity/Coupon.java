package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.enumerate.CouponType;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.coupon.CouponResponseM1;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import com.alphanah.alphanahbackend.utility.DateUtils;
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
    @Column(name = "coupon_uuid", nullable = false, updatable = false)
    @GeneratedValue
    private UUID uuid;

    @Column(name = "coupon_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private CouponType type;

    @Column(name = "coupon_value", nullable = false, updatable = false)
    private Long value = -1L;

    @Column(name = "coupon_usage_status", nullable = false)
    private Boolean usageStatus = false;

    @Column(name = "coupon_expired_date", updatable = false)
    private Date expiredDate;

    @Column(name = "coupon_creator_uuid", nullable = false, updatable = false)
    private UUID creatorUuid;

    public boolean isUsed() {
        return usageStatus;
    }

    public boolean isExpired() {
        if (Objects.isNull(expiredDate))
            return false;
        return this.expiredDate.before(new Date());
    }

    public CouponResponseM1 toCouponResponseM1() throws AlphanahBaseException {
        return this.toCouponResponseM1(null);
    }

    private CouponResponseM1 toCouponResponseM1(CouponResponseM1 response) throws AlphanahBaseException {
        if (response == null)
            response = new CouponResponseM1();
        response.setCouponUUID(uuid);
        response.setType(type);
        response.setValue(value);
        response.setUsageStatus(usageStatus);
        response.setCreator(AccountUtils.getAccountWithUuid(creatorUuid).toAccountResponseM1(null));
        response.setExpiredDate(Objects.isNull(expiredDate) ? null : DateUtils.timeZoneConverter(expiredDate, DateTimeZone.forID("Asia/Bangkok")));
        return response;
    }

}