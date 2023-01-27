package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.exception.AccountException;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.account.MGetAccountResponse;
import com.alphanah.alphanahbackend.model.account.MUpdateAccountRequest;
import com.alphanah.alphanahbackend.service.AccountService;
import com.alphanah.alphanahbackend.service.AmazonS3Service;
import com.alphanah.alphanahbackend.utility.PhoneUtil;
import com.alphanah.alphanahbackend.utility.PictureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Objects;

@Service
public class AccountBusiness {

    @Autowired
    private AccountService service;

    @Autowired
    private AmazonS3Service amazonS3Service;

    // Get raw attributes from AWS Cognito for debug and testing
    // Please delete this function soon as possible
    public Map<String, Object> getAccountRawAttributes(String bearerToken) {
        return service.getAccountRawAttributes(bearerToken);
    }

    public MGetAccountResponse getAccount(String bearerToken) {
        return service.getAccount(bearerToken).toMGetAccountResponse();
    }

    public void updateAccount(String bearerToken, MUpdateAccountRequest request) throws AlphanahBaseException {
        if (Objects.isNull(request.getFirstname()))
            throw AccountException.updateFirstnameNull();

        if (Objects.isNull(request.getLastname()))
            throw AccountException.updateLastnameNull();

        if (Objects.isNull(request.getAddress()))
            throw AccountException.updateAddressNull();

        if (Objects.isNull(request.getPhone()))
            throw AccountException.updatePhoneNull();

        if (request.getFirstname().length() > 20)
            throw AccountException.updateFirstnameMaxLength();

        if (request.getLastname().length() > 20)
            throw AccountException.updateLastnameMaxLength();

        if (request.getAddress().length() > 2048)
            throw AccountException.updateAddressMaxLength();

        if (request.getPhone().length() > 19)
            throw AccountException.updatePhoneMaxLength();

        service.updateAccount(bearerToken, "name", request.getFirstname());
        service.updateAccount(bearerToken, "family_name", request.getLastname());
        service.updateAccount(bearerToken, "address", request.getAddress());
        service.updateAccount(bearerToken, "phone_number", PhoneUtil.addThaiAreaCode(request.getPhone()));
    }

    public void updateAccountPicture(String bearerToken, MultipartFile file) throws AlphanahBaseException {
        PictureUtil.validateFile(file);
        service.updateAccount(bearerToken, "picture", amazonS3Service.saveFile(file));
    }

}
