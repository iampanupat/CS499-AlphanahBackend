package com.alphanah.alphanahbackend;

import com.alphanah.alphanahbackend.enumerate.CouponType;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.service.CouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class CouponServiceUnitTest {

    @Autowired
    CouponService service;

    @Test
    void test() throws AlphanahBaseException {
        service.createCoupon(UUID.randomUUID(), CouponType.FREE_SHIPPING, -2, null);
    }


}
