package com.alphanah.alphanahbackend.repository;

import com.alphanah.alphanahbackend.entity.Coupon;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CouponRepository extends CrudRepository<Coupon, String> {

}
