package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.ProductCategoryBusiness;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.ListResponse;
import com.alphanah.alphanahbackend.model.product_category.ProductCategoryResponseM1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductCategoryAPI {

    @Autowired
    private ProductCategoryBusiness business;

    @PostMapping("/{productUuid}/category/{categoryUuid}")
    public ResponseEntity<ListResponse> createProductCategory(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("productUuid") UUID productUuid,
            @PathVariable("categoryUuid") UUID categoryUuid
    ) throws AlphanahBaseException {
        List<ProductCategoryResponseM1> rawResponse = business.createProductCategory(token, productUuid, categoryUuid);
        ListResponse response = new ListResponse(rawResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{productUuid}/category/{categoryUuid}")
    public ResponseEntity<Void> deleteProductCategory(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("productUuid") UUID productUuid,
            @PathVariable("categoryUuid") UUID categoryUuid
    ) throws AlphanahBaseException {
        business.deleteProductCategory(token, productUuid, categoryUuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
