package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.ProductCategoryBusiness;
import com.alphanah.alphanahbackend.entity.ProductCategory;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.product_category.ProductCategoryResponseM1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductCategoryApi {

    @Autowired
    private ProductCategoryBusiness business;

    @PostMapping("/{productUuid}/category/{categoryUuid}")
    public ResponseEntity<List<ProductCategoryResponseM1>> createProductCategory(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("productUuid") UUID productUuid,
            @PathVariable("categoryUuid") UUID categoryUuid
    ) throws AlphanahBaseException {
        List<ProductCategoryResponseM1> responses = business.createProductCategory(token, productUuid, categoryUuid);
        return new ResponseEntity<>(responses, HttpStatus.OK);
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
