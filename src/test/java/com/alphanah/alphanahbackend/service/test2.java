package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Category;
import com.alphanah.alphanahbackend.enumerate.CouponType;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.repository.CategoryRepository;
import com.alphanah.alphanahbackend.service.CouponService;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

//@SpringBootTest(classes = {com.alphanah.alphanahbackend.AlphanahBackendApplication.class})
@DataJpaTest
public class test2 {

    @Autowired
    CouponService service;

    @Test
    void test() throws AlphanahBaseException {
        service.createCoupon(UUID.randomUUID(), CouponType.FREE_SHIPPING, -2, null);
    }


}
