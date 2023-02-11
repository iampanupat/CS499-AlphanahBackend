package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Order;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.OrderException;
import com.alphanah.alphanahbackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> getAll(UUID creatorUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderException.getAllWithNullCreatorUuid();

        return repository.findAllByCreatorUuid(creatorUuid.toString());
    }

    public Order get(UUID creatorUuid, UUID uuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderException.getWithNullCreatorUuid();

        if (Objects.isNull(uuid))
            throw OrderException.getWithNullUuid();

        Optional<Order> optional = repository.findById(uuid.toString());
        if (optional.isEmpty())
            throw OrderException.getNullObject();

        return optional.get();
    }

    public Order create(UUID creatorUuid, String type) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderException.createWithNullCreatorUuid();

        if (Objects.isNull(type))
            throw OrderException.createWithNullType();

        return null;
    }

}
