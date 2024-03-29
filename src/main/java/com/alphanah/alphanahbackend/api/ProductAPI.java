package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.ProductBusiness;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.ListResponse;
import com.alphanah.alphanahbackend.model.product.ProductRequest;
import com.alphanah.alphanahbackend.model.product.ProductResponseM1;
import com.alphanah.alphanahbackend.model.product.ProductResponseM3;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductAPI {

    @Autowired
    private ProductBusiness business;

    @GetMapping
    public ResponseEntity<ListResponse> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) UUID merchant
    ) throws AlphanahBaseException {
        List<ProductResponseM3> rawResponse = business.getAllProducts(name, category, merchant);
        ListResponse response = new ListResponse(rawResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProductResponseM3> getProduct(@PathVariable("uuid") UUID uuid) throws AlphanahBaseException {
        ProductResponseM3 response = business.getProduct(uuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponseM1> createProduct(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody ProductRequest request
    ) throws AlphanahBaseException {
        AccountUtils.merchantVerify(token);
        ProductResponseM1 response = business.createProduct(token, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ProductResponseM1> updateProduct(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody ProductRequest request,
            @PathVariable("uuid") UUID uuid
    ) throws AlphanahBaseException {
        AccountUtils.merchantVerify(token);
        ProductResponseM1 response = business.updateProduct(token, uuid, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteProduct(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("uuid") UUID uuid
    ) throws AlphanahBaseException {
        AccountUtils.merchantVerify(token);
        business.deleteProduct(token, uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}