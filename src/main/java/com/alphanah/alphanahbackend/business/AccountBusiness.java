package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.Account;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.account.AccountRequest;
import com.alphanah.alphanahbackend.enumerate.AwsCognitoField;
import com.alphanah.alphanahbackend.model.account.AccountResponseM1;
import com.alphanah.alphanahbackend.model.account.AccountResponseM2;
import com.alphanah.alphanahbackend.service.AccountService;
import com.alphanah.alphanahbackend.service.AmazonS3Service;
import com.alphanah.alphanahbackend.utility.PictureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class AccountBusiness {

    @Autowired
    private AccountService service;

    @Autowired
    private AmazonS3Service amazonS3Service;

    public AccountResponseM2 getAccountWithToken(String token) throws AlphanahBaseException {
        Account response = service.findAccount(token);
        return response.toAccountResponseM2();
    }

    public AccountResponseM1 getAccountWithUuid(UUID accountUuid) throws AlphanahBaseException {
        Account response = service.findAccount(accountUuid);
        return response.toAccountResponseM1();
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