package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.ImageBusiness;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.response.MImageBaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ImageApi {

    @Autowired
    private ImageBusiness business;

    @GetMapping("/{productUuid}/image")
    public ResponseEntity<List<MImageBaseResponse>> getAllProductImages(
            @PathVariable("productUuid") UUID productUuid
    ) throws AlphanahBaseException {
        List<MImageBaseResponse> response = business.getAllProductImages(productUuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{productUuid}/image")
    public ResponseEntity<MImageBaseResponse> createProductImage(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody MultipartFile image,
            @PathVariable("productUuid") UUID productUuid
    ) throws AlphanahBaseException {
        MImageBaseResponse response = business.createProductImage(token, productUuid, image);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{productUuid}/image/{uuid}")
    public ResponseEntity<Void> deleteProductImage(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("productUuid") UUID productUuid,
            @PathVariable("uuid") UUID uuid
    ) throws AlphanahBaseException {
        business.deleteProductImage(token, productUuid, uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{productUuid}/review/{reviewUuid}/image")
    public ResponseEntity<List<MImageBaseResponse>> getAllReviewImages(
            @PathVariable("productUuid") UUID productUuid,
            @PathVariable("reviewUuid") UUID reviewUuid
    ) throws AlphanahBaseException {
        List<MImageBaseResponse> response = business.getAllReviewImages(productUuid, reviewUuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{productUuid}/review/{reviewUuid}/image")
    public ResponseEntity<MImageBaseResponse> createReviewImage(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody MultipartFile image,
            @PathVariable("productUuid") UUID productUuid,
            @PathVariable("reviewUuid") UUID reviewUuid
    ) throws AlphanahBaseException {
        MImageBaseResponse response = business.createReviewImage(token, productUuid, reviewUuid, image);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{productUuid}/review/{reviewUuid}/image/{uuid}")
    public ResponseEntity<Void> deleteReviewImage(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("productUuid") UUID productUuid,
            @PathVariable("reviewUuid") UUID reviewUuid,
            @PathVariable("uuid") UUID uuid
    ) throws AlphanahBaseException {
        business.deleteReviewImage(token, productUuid, reviewUuid, uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
