package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.Account;
import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.account.AccountRequest;
import com.alphanah.alphanahbackend.enumerate.AwsCognitoField;
import com.alphanah.alphanahbackend.model.account.AccountResponseM1;
import com.alphanah.alphanahbackend.model.account.AccountResponseM2;
import com.alphanah.alphanahbackend.service.AccountService;
import com.alphanah.alphanahbackend.service.AmazonS3Service;
import com.alphanah.alphanahbackend.service.ProductService;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import com.alphanah.alphanahbackend.utility.PictureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class AccountBusiness {

    @Autowired
    private AccountService service;

    @Autowired
    private AmazonS3Service amazonS3Service;

    @Autowired
    private ProductService productService;

    public AccountResponseM2 getAccountWithToken(String token) throws AlphanahBaseException {
        Account account = service.findAccount(token);
        long productCount = 0L;
        long reviewCount = 0L;
        List<Product> productList = productService.findAllProducts();
        for (Product prod: productList) {
            if (prod.getCreatorUuid().equals((account.getUuid()))) {
                productCount++;
                reviewCount += prod.getReviews().size();
            }
        }
        AccountResponseM2 response = account.toAccountResponseM2();
        response.setProductCount(productCount);
        response.setReviewCount(reviewCount);
        return response;
    }

    public AccountResponseM1 getAccountWithUuid(UUID accountUuid) throws AlphanahBaseException {
        long productCount = 0L;
        long reviewCount = 0L;
        List<Product> productList = productService.findAllProducts();
        for (Product prod: productList) {
            if (prod.getCreatorUuid().equals((accountUuid))) {
                productCount++;
                reviewCount += prod.getReviews().size();
            }
        }
        AccountResponseM1 response = service.findAccount(accountUuid).toAccountResponseM1();
        response.setProductCount(productCount);
        response.setReviewCount(reviewCount);
        return response;
    }

    public AccountResponseM2 updateAccount(String token, AccountRequest request) throws AlphanahBaseException {
        service.updateAccount(token, request.getFirstname(), request.getLastname(), request.getAddress(), request.getPhone());
        Account response = service.findAccount(token);
        return response.toAccountResponseM2();
    }

    public AccountResponseM2 updateAccountImage(String token, MultipartFile image) throws AlphanahBaseException {
        PictureUtils.validateFile(image);
        service.updateAwsCognitoField(service.findAccount(token), AwsCognitoField.IMAGE, amazonS3Service.saveFile(image));
        Account response = service.findAccount(token);
        return response.toAccountResponseM2();
    }

}