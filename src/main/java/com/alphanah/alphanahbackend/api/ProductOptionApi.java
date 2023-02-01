package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.ProductOptionBusiness;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.product.option.MCreateProductOptionRequest;
import com.alphanah.alphanahbackend.model.product.option.MUpdateProductOptionRequest;
import com.alphanah.alphanahbackend.model.response.MProductOptionBaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductOptionApi {

    @Autowired
    private ProductOptionBusiness business;

    @GetMapping("/{productUuid}/option")
    public ResponseEntity<List<MProductOptionBaseResponse>> getAllProductOptions(@PathVariable("productUuid") UUID productUuid) throws AlphanahBaseException {
        List<MProductOptionBaseResponse> response = business.getAllProductOptions(productUuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{productUuid}/option/{uuid}")
    public ResponseEntity<MProductOptionBaseResponse> getProductOption(@PathVariable("productUuid") UUID productUuid, @PathVariable("uuid") UUID uuid) throws AlphanahBaseException {
        MProductOptionBaseResponse response = business.getProductOptions(productUuid, uuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{productUuid}/option")
    public ResponseEntity<MProductOptionBaseResponse> createProductOption(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody MCreateProductOptionRequest request,
            @PathVariable("productUuid") UUID productUuid
    ) throws AlphanahBaseException {
        MProductOptionBaseResponse response = business.createProductOption(token, productUuid, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{productUuid}/option/{uuid}")
    public ResponseEntity<MProductOptionBaseResponse> updateProductOption(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody MUpdateProductOptionRequest request,
            @PathVariable("productUuid") UUID productUuid,
            @PathVariable("uuid") UUID uuid
    ) throws AlphanahBaseException {
        MProductOptionBaseResponse response = business.updateProductOption(token, productUuid, uuid, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{productUuid}/option/{uuid}")
    public ResponseEntity<Void> deleteProductOption(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("productUuid") UUID productUuid,
            @PathVariable("uuid") UUID uuid
    ) throws AlphanahBaseException {
        business.deleteProductOption(token, productUuid, uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}