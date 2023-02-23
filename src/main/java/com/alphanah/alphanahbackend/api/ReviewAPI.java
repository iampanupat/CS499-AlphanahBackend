package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.ReviewBusiness;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.ListResponse;
import com.alphanah.alphanahbackend.model.review.ReviewResponseM1;
import com.alphanah.alphanahbackend.model.review.ReviewResponseM3;
import com.alphanah.alphanahbackend.model.review.ReviewRequest;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ReviewAPI {

    @Autowired
    private ReviewBusiness business;

    @GetMapping("/{productUuid}/review")
    public ResponseEntity<ListResponse> getAllReviews(@PathVariable("productUuid") UUID productUuid) throws AlphanahBaseException {
        List<ReviewResponseM3> rawResponse = business.getAllReviews(productUuid);
        ListResponse response = new ListResponse(rawResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{productUuid}/review/{uuid}")
    public ResponseEntity<ReviewResponseM3> getReview(@PathVariable("productUuid") UUID productUuid, @PathVariable("uuid") UUID uuid) throws AlphanahBaseException {
        ReviewResponseM3 response = business.getReview(productUuid, uuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{productUuid}/review")
    public ResponseEntity<ReviewResponseM1> createReview(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody ReviewRequest request,
            @PathVariable("productUuid") UUID productUuid
    ) throws AlphanahBaseException {
        AccountUtils.customerVerify(token);
        ReviewResponseM1 response = business.createReview(token, productUuid, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{productUuid}/review/{uuid}")
    public ResponseEntity<ReviewResponseM1> updateReview(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody ReviewRequest request,
            @PathVariable("productUuid") UUID productUuid,
            @PathVariable("uuid") UUID uuid
    ) throws AlphanahBaseException {
        AccountUtils.customerVerify(token);
        ReviewResponseM1 response = business.updateReview(token, productUuid, uuid, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{productUuid}/review/{uuid}")
    public ResponseEntity<Void> deleteReview(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("productUuid") UUID productUuid,
            @PathVariable("uuid") UUID uuid
    ) throws AlphanahBaseException {
        AccountUtils.customerVerify(token);
        business.deleteReview(token, productUuid, uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
