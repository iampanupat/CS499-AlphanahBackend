package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.entity.Review;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.ReviewException;
import com.alphanah.alphanahbackend.repository.ReviewRepository;
import com.alphanah.alphanahbackend.utility.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private ProductService productService;

    public List<Review> findAllReviews(UUID productUuid) throws AlphanahBaseException {
        if (Objects.isNull(productUuid))
            throw ReviewException.cannotFindWithNullProductUuid();

        Product product = productService.findProduct(productUuid);
        return repository.findAllByProduct(product);
    }

    public Review findReview(UUID productUuid, UUID reviewUuid) throws AlphanahBaseException {
        if (Objects.isNull(productUuid))
            throw ReviewException.cannotFindWithNullProductUuid();

        if (Objects.isNull(reviewUuid))
            throw ReviewException.cannotFindWithNullReviewUuid();

        Product product = productService.findProduct(productUuid);

        Optional<Review> optional = repository.findById(reviewUuid);
        if (optional.isEmpty())
            throw ReviewException.notFound();

        if (!optional.get().getProduct().getUuid().equals(product.getUuid()))
            throw ReviewException.cannotFindWithInvalidProductUuid();

        return optional.get();
    }

    public Review createReview(UUID creatorUuid, UUID productUuid, String message, Integer rating) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ReviewException.cannotCreateWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ReviewException.cannotCreateWithNullProductUuid();

        if (Objects.isNull(message))
            throw ReviewException.cannotCreateWithNullMessage();

        if (Objects.isNull(rating))
            throw ReviewException.cannotCreateWithNullRating();

        if (message.isEmpty())
            throw ReviewException.cannotCreateWithEmptyMessage();

        if (rating < Env.REVIEW_RATING_MIN_VALUE)
            throw ReviewException.cannotCreateWithRatingLessMinValue();

        if (message.length() > Env.REVIEW_MESSAGE_MAX_LENGTH)
            throw ReviewException.cannotCreateWithMessageExceedMaxLength();

        if (rating > Env.REVIEW_RATING_MAX_VALUE)
            throw ReviewException.cannotCreateWithRatingExceedMaxValue();

        Product product = productService.findProduct(productUuid);

        Review entity = new Review();
        entity.setCreatorUuid(creatorUuid);
        entity.setProduct(product);
        entity.setMessage(message);
        entity.setRating(rating);
        entity.setCreateDate(new Date());
        return repository.save(entity);
    }

    public Review updateReview(UUID creatorUuid, UUID productUuid, UUID reviewUuid, String message, Integer rating) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ReviewException.cannotUpdateWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ReviewException.cannotUpdateWithNullProductUuid();

        if (Objects.isNull(reviewUuid))
            throw ReviewException.cannotUpdateWithNullReviewUuid();

        if (Objects.isNull(message))
            throw ReviewException.cannotUpdateWithNullMessage();

        if (Objects.isNull(rating))
            throw ReviewException.cannotUpdateWithNullRating();

        if (message.isEmpty())
            throw ReviewException.cannotUpdateWithEmptyMessage();

        if (rating < Env.REVIEW_RATING_MIN_VALUE)
            throw ReviewException.cannotUpdateWithRatingLessMinValue();

        if (message.length() > Env.REVIEW_MESSAGE_MAX_LENGTH)
            throw ReviewException.cannotUpdateWithMessageExceedMaxLength();

        if (rating > Env.REVIEW_RATING_MAX_VALUE)
            throw ReviewException.cannotUpdateWithRatingExceedMaxValue();

        Review entity = this.findReview(productUuid, reviewUuid);

        if (!entity.getCreatorUuid().equals(creatorUuid))
            throw ReviewException.cannotUpdateNotOwned();

        entity.setMessage(message);
        entity.setRating(rating);
        return repository.save(entity);
    }

    public void deleteReview(UUID creatorUuid, UUID productUuid, UUID reviewUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ReviewException.cannotDeleteWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ReviewException.cannotDeleteWithNullProductUuid();

        if (Objects.isNull(reviewUuid))
            throw ReviewException.cannotDeleteWithNullReviewUuid();

        Review entity = this.findReview(productUuid, reviewUuid);

        if (!entity.getCreatorUuid().equals(creatorUuid))
            throw ReviewException.cannotDeleteNotOwned();

        repository.delete(entity);
    }

}