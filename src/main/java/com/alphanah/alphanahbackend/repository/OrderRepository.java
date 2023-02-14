package com.alphanah.alphanahbackend.repository;

import com.alphanah.alphanahbackend.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<Order, UUID> {

    List<Order> findAllByCreatorUuid(UUID creatorUuid);

}
