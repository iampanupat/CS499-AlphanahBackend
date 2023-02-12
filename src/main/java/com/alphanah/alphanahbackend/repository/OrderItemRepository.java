package com.alphanah.alphanahbackend.repository;

import com.alphanah.alphanahbackend.entity.Order;
import com.alphanah.alphanahbackend.entity.OrderItem;
import com.alphanah.alphanahbackend.entity.ProductOption;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderItemRepository extends CrudRepository<OrderItem, String> {

    Optional<OrderItem> findByOrderAndProductOption(Order order, ProductOption productOption);

    boolean existsByOrderAndProductOption(Order order, ProductOption productOption);

}
