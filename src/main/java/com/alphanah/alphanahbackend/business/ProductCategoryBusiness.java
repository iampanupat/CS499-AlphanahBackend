package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.ProductCategory;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.product_category.ProductCategoryResponseM1;
import com.alphanah.alphanahbackend.service.ProductCategoryService;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductCategoryBusiness {

    @Autowired
    private ProductCategoryService service;

    public List<ProductCategoryResponseM1> createProductCategory(String token, UUID productUuid, UUID categoryUuid) throws AlphanahBaseException {
        List<ProductCategoryResponseM1> responses = new ArrayList<>();
        List<ProductCategory> productCategories = service.create(AccountUtils.getAccountWithToken(token).getUuid(), productUuid, categoryUuid);

        for (ProductCategory productCategory : productCategories)
            responses.add(productCategory.toProductCategoryResponseM1(null));

        return responses;
    }

    public void deleteProductCategory(String token, UUID productUuid, UUID categoryUuid) throws AlphanahBaseException {
        service.delete(AccountUtils.getAccountWithToken(token).getUuid(), productUuid, categoryUuid);
    }

}
