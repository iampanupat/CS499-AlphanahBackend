package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.CategoryBusiness;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.category.MCreateCategoryRequest;
import com.alphanah.alphanahbackend.model.category.MUpdateCategoryRequest;
import com.alphanah.alphanahbackend.model.response.MCategoryBaseResponse;
import com.alphanah.alphanahbackend.model.response.MCategoryFullResponse;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryApi {

    @Autowired
    private CategoryBusiness business;

    @Autowired
    private AccountUtils accountUtils;

    @GetMapping
    public ResponseEntity<List<MCategoryFullResponse>> getAllCategories() {
        List<MCategoryFullResponse> response = business.getAllCategories();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<MCategoryFullResponse> getCategory(@PathVariable("uuid") UUID uuid) throws AlphanahBaseException {
        MCategoryFullResponse response = business.getCategory(uuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<MCategoryBaseResponse> createCategory(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody MCreateCategoryRequest request
    ) throws AlphanahBaseException {
        accountUtils.merchantVerify(token);
        MCategoryBaseResponse response = business.createCategory(token, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<MCategoryBaseResponse> updateCategory(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody MUpdateCategoryRequest request,
            @PathVariable("uuid") UUID uuid
    ) throws AlphanahBaseException {
        accountUtils.merchantVerify(token);
        MCategoryBaseResponse response = business.updateCategory(token, uuid, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteCategory(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("uuid") UUID uuid
    ) throws AlphanahBaseException {
        accountUtils.merchantVerify(token);
        business.deleteCategory(token, uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}