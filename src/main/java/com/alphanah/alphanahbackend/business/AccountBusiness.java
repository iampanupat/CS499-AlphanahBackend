package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.Account;
import com.alphanah.alphanahbackend.exception.AccountException;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.account.MUpdateAccountRequest;
import com.alphanah.alphanahbackend.model.enumerate.ECognitoField;
import com.alphanah.alphanahbackend.model.response.MAccountFullResponse;
import com.alphanah.alphanahbackend.service.AccountService;
import com.alphanah.alphanahbackend.service.AmazonS3Service;
import com.alphanah.alphanahbackend.utility.PhoneUtils;
import com.alphanah.alphanahbackend.utility.PictureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

@Service
public class AccountBusiness {

    @Autowired
    private AccountService service;

    @Autowired
    private AmazonS3Service amazonS3Service;

    private final int ACCOUNT_FIRSTNAME_MAX_LENGTH = 20;
    private final int ACCOUNT_LASTNAME_MAX_LENGTH = 20;
    private final int ACCOUNT_ADDRESS_MAX_LENGTH = 2048;
    private final int ACCOUNT_PHONE_MAX_LENGTH = 19;

    public MAccountFullResponse getAccount(String token) throws AlphanahBaseException {
        return service.get(token).toMAccountFullResponse();
    }

    public MAccountFullResponse getAccountByUuid(UUID uuid) throws AlphanahBaseException {
        return service.get(uuid).toMAccountFullResponse();
    }

    public MAccountFullResponse updateAccount(String token, MUpdateAccountRequest request) throws AlphanahBaseException {
        if (Objects.isNull(token))
            throw AccountException.updateWithNullToken();

        if (Objects.isNull(request.getFirstname()))
            throw AccountException.updateWithNullFirstname();

        if (Objects.isNull(request.getLastname()))
            throw AccountException.updateWithNullLastname();

        if (Objects.isNull(request.getAddress()))
            throw AccountException.updateWithNullAddress();

        if (Objects.isNull(request.getPhone()))
            throw AccountException.updateWithNullPhone();

        if (request.getFirstname().length() > ACCOUNT_FIRSTNAME_MAX_LENGTH)
            throw AccountException.updateWithMaxLengthFirstname();

        if (request.getLastname().length() > ACCOUNT_LASTNAME_MAX_LENGTH)
            throw AccountException.updateWithMaxLengthLastname();

        if (request.getAddress().length() > ACCOUNT_ADDRESS_MAX_LENGTH)
            throw AccountException.updateWithMaxLengthAddress();

        if (request.getPhone().length() > ACCOUNT_PHONE_MAX_LENGTH)
            throw AccountException.updateWithMaxLengthPhone();

        service.update(token, ECognitoField.FIRSTNAME, request.getFirstname());
        service.update(token, ECognitoField.LASTNAME, request.getLastname());
        service.update(token, ECognitoField.ADDRESS, request.getAddress());
        service.update(token, ECognitoField.PHONE, PhoneUtils.addThaiAreaCode(request.getPhone()));
        return service.get(token).toMAccountFullResponse();
    }

    public MAccountFullResponse updateAccountPicture(String token, MultipartFile file) throws AlphanahBaseException {
        PictureUtils.validateFile(file);
        service.update(token, ECognitoField.IMAGE, amazonS3Service.saveFile(file));
        return service.get(token).toMAccountFullResponse();
    }

}
