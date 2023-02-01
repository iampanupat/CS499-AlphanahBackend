package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.ReviewBusiness;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.response.MReviewBaseResponse;
import com.alphanah.alphanahbackend.model.response.MReviewFullResponse;
import com.alphanah.alphanahbackend.model.review.MCreateReviewRequest;
import com.alphanah.alphanahbackend.model.review.MUpdateReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ReviewApi {

    @Autowired
    private ReviewBusiness business;

    @GetMapping("/{productUuid}/review")
    public ResponseEntity<List<MReviewFullResponse>> getAllReviews(@PathVariable("productUuid") UUID productUuid) throws AlphanahBaseException {
        List<MReviewFullResponse> response = business.getAllReviews(productUuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{productUuid}/review/{uuid}")
    public ResponseEntity<MReviewFullResponse> getReview(@PathVariable("productUuid") UUID productUuid, @PathVariable("uuid") UUID uuid) throws AlphanahBaseException {
        MReviewFullResponse response = business.getReview(productUuid, uuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{productUuid}/review")
    public ResponseEntity<MReviewBaseResponse> createReview(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody MCreateReviewRequest request,
            @PathVariable("productUuid") UUID productUuid
    ) throws AlphanahBaseException {
        MReviewBaseResponse response = business.createReview(token, productUuid, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{productUuid}/review/{uuid}")
    public ResponseEntity<MReviewBaseResponse> updateReview(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody MUpdateReviewRequest request,
            @PathVariable("productUuid") UUID productUuid,
            @PathVariable("uuid") UUID uuid
    ) throws AlphanahBaseException {
        MReviewBaseResponse response = business.updateReview(token, productUuid, uuid, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{productUuid}/review/{uuid}")
    public ResponseEntity<Void> deleteReview(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("productUuid") UUID productUuid,
            @PathVariable("uuid") UUID uuid
    ) throws AlphanahBaseException {
        business.deleteReview(token, productUuid, uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
