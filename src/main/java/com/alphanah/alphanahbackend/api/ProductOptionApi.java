package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.ProductOptionBusiness;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.ListResponse;
import com.alphanah.alphanahbackend.model.product_option.ProductOptionRequest;
import com.alphanah.alphanahbackend.model.product_option.ProductOptionResponseM1;
import jakarta.validation.Valid;
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
    public ResponseEntity<ListResponse> getAllProductOptions(@PathVariable("productUuid") UUID productUuid) throws AlphanahBaseException {
        List<ProductOptionResponseM1> rawResponse = business.getAllProductOptions(productUuid);
        ListResponse response = new ListResponse(rawResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{productUuid}/option/{uuid}")
    public ResponseEntity<ProductOptionResponseM1> getProductOption(@PathVariable("productUuid") UUID productUuid, @PathVariable("uuid") UUID uuid) throws AlphanahBaseException {
        ProductOptionResponseM1 response = business.getProductOptions(productUuid, uuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{productUuid}/option")
    public ResponseEntity<ProductOptionResponseM1> createProductOption(
            @RequestHeader(value = "Authorization") String token,
            @Valid @RequestBody ProductOptionRequest request,
            @PathVariable("productUuid") UUID productUuid
    ) throws AlphanahBaseException {
        ProductOptionResponseM1 response = business.createProductOption(token, productUuid, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{productUuid}/option/{uuid}")
    public ResponseEntity<ProductOptionResponseM1> updateProductOption(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody ProductOptionRequest request,
            @PathVariable("productUuid") UUID productUuid,
            @PathVariable("uuid") UUID uuid
    ) throws AlphanahBaseException {
        ProductOptionResponseM1 response = business.updateProductOption(token, productUuid, uuid, request);
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