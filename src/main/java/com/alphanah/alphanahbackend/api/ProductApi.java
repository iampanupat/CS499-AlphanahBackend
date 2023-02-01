package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.ProductBusiness;
import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.product.root.MCreateProductRequest;
import com.alphanah.alphanahbackend.model.response.MProductBaseResponse;
import com.alphanah.alphanahbackend.model.response.MProductFullResponse;
import com.alphanah.alphanahbackend.model.product.root.MUpdateProductRequest;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductApi {

    @Autowired
    private ProductBusiness business;

    @Autowired
    private AccountUtils accountUtils;

    @GetMapping
    public ResponseEntity<List<MProductFullResponse>> getAllProducts() {
        List<MProductFullResponse> response = business.getAllProducts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<MProductFullResponse> getProduct(@PathVariable("uuid") UUID uuid) throws AlphanahBaseException {
        MProductFullResponse response = business.getProduct(uuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MProductBaseResponse> createProduct(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody MCreateProductRequest request
    ) throws AlphanahBaseException {
        accountUtils.merchantVerify(token);
        MProductBaseResponse response = business.createProduct(token, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<MProductBaseResponse> updateProduct(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody MUpdateProductRequest request,
            @PathVariable("uuid") UUID uuid
    ) throws AlphanahBaseException {
        accountUtils.merchantVerify(token);
        MProductBaseResponse response = business.updateProduct(token, uuid, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteProduct(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("uuid") UUID uuid
    ) throws AlphanahBaseException {
        accountUtils.merchantVerify(token);
        business.deleteProduct(token, uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}