package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.entity.Review;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.ReviewException;
import com.alphanah.alphanahbackend.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private ProductService productService;

    private final int REVIEW_HEADER_MAX_LENGTH = 120;
    private final int REVIEW_MESSAGE_MAX_LENGTH = 255;
    private final int REVIEW_RATING_MAX_VALUE = 5;

    public List<Review> getAll(UUID productUuid) throws AlphanahBaseException {
        if (Objects.isNull(productUuid))
            throw ReviewException.getAllWithNullProductUuid();

        Product product;
        try {
            product = productService.get(productUuid);
        } catch (AlphanahBaseException exception) {
            throw ReviewException.getNullProductObject();
        }

        return repository.findAllByProductReviewOwner(product);
    }

    public Review get(UUID productUuid, UUID uuid) throws AlphanahBaseException {
        if (Objects.isNull(productUuid))
            throw ReviewException.getWithNullProductUuid();

        if (Objects.isNull(uuid))
            throw ReviewException.getWithNullUuid();

        Product product;
        try {
            product = productService.get(productUuid);
        } catch (AlphanahBaseException exception) {
            throw ReviewException.getWithNullProductObject();
        }

        Optional<Review> optional = repository.findById(uuid.toString());
        if (optional.isEmpty())
            throw ReviewException.getNullObject();

        if (!optional.get().getProductReviewOwner().getUuid().equals(product.getUuid()))
            throw ReviewException.getWithInvalidProductUuid();

        return optional.get();
    }

    public Review create(UUID creatorUuid, UUID productUuid, String header, String message, int rating) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ReviewException.createWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ReviewException.createWithNullProductUuid();

        if (Objects.isNull(header))
            throw ReviewException.createWithNullHeader();

        if (Objects.isNull(message))
            throw ReviewException.createWithNullMessage();

        if (header.isEmpty())
            throw ReviewException.createWithEmptyHeader();

        if (message.isEmpty())
            throw ReviewException.createWithEmptyMessage();

        if (rating < 0)
            throw ReviewException.createWithNegativeRating();

        if (header.length() > REVIEW_HEADER_MAX_LENGTH)
            throw ReviewException.createWithMaxLengthHeader();

        if (message.length() > REVIEW_MESSAGE_MAX_LENGTH)
            throw ReviewException.createWithMaxLengthMessage();

        if (rating > REVIEW_RATING_MAX_VALUE)
            throw ReviewException.createWithMaxValueRating();

        Product product;
        try {
            product = productService.get(productUuid);
        } catch (AlphanahBaseException exception) {
            throw ReviewException.createWithNullProductObject();
        }

        Review entity = new Review();
        entity.setCreatorUuid(creatorUuid.toString());
        entity.setProductReviewOwner(product);
        entity.setHeader(header);
        entity.setMessage(message);
        entity.setRating(rating);
        return repository.save(entity);
    }

    public Review update(UUID creatorUuid, UUID productUuid, UUID uuid, String header, String message, int rating) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ReviewException.updateWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ReviewException.updateWithNullProductUuid();

        if (Objects.isNull(uuid))
            throw ReviewException.updateWithNullUuid();

        if (Objects.isNull(header))
            throw ReviewException.updateWithNullHeader();

        if (Objects.isNull(message))
            throw ReviewException.updateWithNullMessage();

        if (header.isEmpty())
            throw ReviewException.updateWithEmptyHeader();

        if (message.isEmpty())
            throw ReviewException.updateWithEmptyMessage();

        if (rating < 0)
            throw ReviewException.updateWithNegativeRating();

        if (header.length() > REVIEW_HEADER_MAX_LENGTH)
            throw ReviewException.updateWithMaxLengthHeader();

        if (message.length() > REVIEW_MESSAGE_MAX_LENGTH)
            throw ReviewException.updateWithMaxLengthMessage();

        if (rating > REVIEW_RATING_MAX_VALUE)
            throw ReviewException.updateWithMaxValueRating();

        Review entity;
        try {
            entity = this.get(productUuid, uuid);
        } catch (AlphanahBaseException exception) {
            throw ReviewException.updateNullObject();
        }

        if (!entity.getCreatorUuid().equals(creatorUuid.toString()))
            throw ReviewException.updateNotOwned();

        entity.setHeader(header);
        entity.setMessage(message);
        entity.setRating(rating);
        return repository.save(entity);
    }

    public void delete(UUID creatorUuid, UUID productUuid, UUID uuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ReviewException.deleteWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ReviewException.deleteWithNullProductUuid();

        if (Objects.isNull(uuid))
            throw ReviewException.deleteWithNullUuid();

        Review entity;
        try {
            entity = this.get(productUuid, uuid);
        } catch (AlphanahBaseException exception) {
            throw ReviewException.deleteNullObject();
        }

        if (!entity.getCreatorUuid().equals(creatorUuid.toString()))
            throw ReviewException.deleteNotOwned();

        repository.delete(entity);
    }

}
