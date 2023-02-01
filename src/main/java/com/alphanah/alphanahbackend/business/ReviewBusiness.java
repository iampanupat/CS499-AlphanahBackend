package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.Review;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.response.MReviewBaseResponse;
import com.alphanah.alphanahbackend.model.response.MReviewFullResponse;
import com.alphanah.alphanahbackend.model.review.MCreateReviewRequest;
import com.alphanah.alphanahbackend.model.review.MUpdateReviewRequest;
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

    @Autowired
    private AccountUtils accountUtils;

    public List<MReviewFullResponse> getAllReviews(UUID productUuid) throws AlphanahBaseException {
        List<MReviewFullResponse> responses = new ArrayList<>();
        List<Review> reviews = service.getAll(productUuid);
        for (Review review : reviews)
            responses.add(review.toMReviewFullResponse());
        return responses;
    }

    public MReviewFullResponse getReview(UUID productUuid, UUID uuid) throws AlphanahBaseException {
        return service.get(productUuid, uuid).toMReviewFullResponse();
    }

    public MReviewBaseResponse createReview(String token, UUID productUuid, MCreateReviewRequest request) throws AlphanahBaseException {
        return service.create(accountUtils.getAccount(token).getUuid(), productUuid, request.getHeader(), request.getMessage(), request.getRating()).toMReviewBaseResponse();
    }

    public MReviewBaseResponse updateReview(String token, UUID productUuid, UUID uuid, MUpdateReviewRequest request) throws AlphanahBaseException {
        return service.update(accountUtils.getAccount(token).getUuid(), productUuid, uuid, request.getHeader(), request.getMessage(), request.getRating()).toMReviewBaseResponse();
    }

    public void deleteReview(String token, UUID productUuid, UUID uuid) throws AlphanahBaseException {
        service.delete(accountUtils.getAccount(token).getUuid(), productUuid, uuid);
    }

}
