package com.alphanah.alphanahbackend.repository;

import com.alphanah.alphanahbackend.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, String> {

    List<Order> findAllByCreatorUuid(String creatorUuid);

}
