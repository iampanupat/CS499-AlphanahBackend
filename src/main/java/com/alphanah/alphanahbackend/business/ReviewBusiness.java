package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.Review;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.review.ReviewResponseM1;
import com.alphanah.alphanahbackend.model.review.ReviewResponseM3;
import com.alphanah.alphanahbackend.model.review.ReviewRequest;
import com.alphanah.alphanahbackend.service.ReviewService;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewBusiness {

    @Autowired
    private ReviewService service;

    public List<ReviewResponseM3> getAllReviews(UUID productUuid) throws AlphanahBaseException {
        List<ReviewResponseM3> responses = new ArrayList<>();
        List<Review> reviews = service.findAllReviews(productUuid);
        for (Review review : reviews)
            responses.add(review.toReviewResponseM3(null));
        return responses;
    }

    public ReviewResponseM3 getReview(UUID productUuid, UUID uuid) throws AlphanahBaseException {
        Review response = service.findReview(productUuid, uuid);
        return response.toReviewResponseM3(null);
    }

    public ReviewResponseM1 createReview(String token, UUID productUuid, ReviewRequest request) throws AlphanahBaseException {
        Review response = service.createReview(AccountUtils.findAccount(token).getUuid(), productUuid, request.getMessage(), request.getRating());
        return response.toReviewResponseM1(null);
    }

    public ReviewResponseM1 updateReview(String token, UUID productUuid, UUID uuid, ReviewRequest request) throws AlphanahBaseException {
        Review response = service.updateReview(AccountUtils.findAccount(token).getUuid(), productUuid, uuid, request.getMessage(), request.getRating());
        return response.toReviewResponseM1(null);
    }

    public void deleteReview(String token, UUID productUuid, UUID uuid) throws AlphanahBaseException {
        service.deleteReview(AccountUtils.findAccount(token).getUuid(), productUuid, uuid);
    }

}
