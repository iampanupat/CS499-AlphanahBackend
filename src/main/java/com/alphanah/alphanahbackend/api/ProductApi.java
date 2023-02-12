package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.ProductBusiness;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
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
public class ProductApi {

    @Autowired
    private ProductBusiness business;

    @GetMapping
    public ResponseEntity<List<ProductResponseM3>> getAllProducts() throws AlphanahBaseException {
        List<ProductResponseM3> response = business.getAllProducts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/owned")
    public ResponseEntity<List<ProductResponseM3>> getMyProducts(
            @RequestHeader(value = "Authorization") String token
    ) throws AlphanahBaseException {
        List<ProductResponseM3> response = business.getMyProducts(AccountUtils.getAccountWithToken(token).getUuid());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseM3>> searchProducts(@RequestParam String keyword) throws AlphanahBaseException {
        List<ProductResponseM3> responses = business.searchProducts(keyword);
        return new ResponseEntity<>(responses, HttpStatus.OK);
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