package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.ProductCategory;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.response.MProductCategoryFullResponse;
import com.alphanah.alphanahbackend.service.ProductCategoryService;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductCategoryBusiness {

    @Autowired
    private ProductCategoryService service;

    @Autowired
    private AccountUtils accountUtils;

    public MProductCategoryFullResponse createProductCategory(String token, UUID productUuid, UUID categoryUuid) throws AlphanahBaseException {
        return service.create(accountUtils.getAccount(token).getUuid(), productUuid, categoryUuid).toMProductCategoryFullResponse();
    }

    public void deleteProductCategory(String token, UUID productUuid, UUID categoryUuid) throws AlphanahBaseException {
        service.delete(accountUtils.getAccount(token).getUuid(), productUuid, categoryUuid);
    }

}
