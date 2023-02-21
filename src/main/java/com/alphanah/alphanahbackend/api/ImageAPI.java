package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.ImageBusiness;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.ListResponse;
import com.alphanah.alphanahbackend.model.image.ImageResponseM1;
import com.alphanah.alphanahbackend.model.product.ProductResponseM3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ImageAPI {

    @Autowired
    private ImageBusiness business;

    @GetMapping("/{productUuid}/image")
    public ResponseEntity<ListResponse> getAllProductImages(
            @PathVariable("productUuid") UUID productUuid
    ) throws AlphanahBaseException {
        List<ImageResponseM1> rawResponse = business.getAllProductImages(productUuid);
        ListResponse response = new ListResponse(rawResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{productUuid}/main_image")
    public ResponseEntity<ProductResponseM3> createProductMainImage(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody MultipartFile image,
            @PathVariable("productUuid") UUID productUuid
    ) throws AlphanahBaseException {
        ProductResponseM3 response = business.createProductMainImage(token, productUuid, image);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{productUuid}/image")
    public ResponseEntity<ImageResponseM1> createProductImage(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody MultipartFile image,
            @PathVariable("productUuid") UUID productUuid
    ) throws AlphanahBaseException {
        ImageResponseM1 response = business.createProductImage(token, productUuid, image);
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
    public ResponseEntity<ListResponse> getAllReviewImages(
            @PathVariable("productUuid") UUID productUuid,
            @PathVariable("reviewUuid") UUID reviewUuid
    ) throws AlphanahBaseException {
        List<ImageResponseM1> rawResponse = business.getAllReviewImages(productUuid, reviewUuid);
        ListResponse response = new ListResponse(rawResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{productUuid}/review/{reviewUuid}/image")
    public ResponseEntity<ImageResponseM1> createReviewImage(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody MultipartFile image,
            @PathVariable("productUuid") UUID productUuid,
            @PathVariable("reviewUuid") UUID reviewUuid
    ) throws AlphanahBaseException {
        ImageResponseM1 response = business.createReviewImage(token, productUuid, reviewUuid, image);
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
