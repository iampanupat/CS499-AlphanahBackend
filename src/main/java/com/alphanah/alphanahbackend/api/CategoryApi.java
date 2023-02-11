package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.CategoryBusiness;
import com.alphanah.alphanahbackend.entity.Category;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.category.CategoryRequest;
import com.alphanah.alphanahbackend.model.category.CategoryResponseM2;
import com.alphanah.alphanahbackend.model.category.CategoryResponseM3;
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

    @GetMapping
    public ResponseEntity<List<CategoryResponseM3>> getAllCategories() throws AlphanahBaseException {
        List<CategoryResponseM3> response = business.getAllCategories();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CategoryResponseM3> getCategory(@PathVariable("uuid") UUID uuid) throws AlphanahBaseException {
        CategoryResponseM3 response = business.getCategory(uuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseM2> createCategory(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody CategoryRequest request
    ) throws AlphanahBaseException {
        AccountUtils.merchantVerify(token);
        CategoryResponseM2 response = business.createCategory(null, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{uuid}")
    public ResponseEntity<CategoryResponseM2> createSubCategory(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody CategoryRequest request,
            @PathVariable("uuid") UUID parentUuid
    ) throws AlphanahBaseException {
        AccountUtils.merchantVerify(token);
        CategoryResponseM2 response = business.createCategory(parentUuid, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}