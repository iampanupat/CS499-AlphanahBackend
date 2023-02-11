package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.Account;
import com.alphanah.alphanahbackend.exception.AccountException;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.account.UpdateAccountRequest;
import com.alphanah.alphanahbackend.enumerate.CognitoField;
import com.alphanah.alphanahbackend.model.account.AccountResponseM1;
import com.alphanah.alphanahbackend.model.account.AccountResponseM2;
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

    public AccountResponseM2 getAccount(String token) throws AlphanahBaseException {
        Account response = service.get(token);
        return response.toAccountResponseM2(null);
    }

    public AccountResponseM1 getAccountByUuid(UUID uuid) throws AlphanahBaseException {
        Account response = service.get(uuid);
        return response.toAccountResponseM1(null);
    }

    public AccountResponseM2 updateAccount(String token, UpdateAccountRequest request) throws AlphanahBaseException {
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

        service.update(token, CognitoField.FIRSTNAME, request.getFirstname());
        service.update(token, CognitoField.LASTNAME, request.getLastname());
        service.update(token, CognitoField.ADDRESS, request.getAddress());
        service.update(token, CognitoField.PHONE, PhoneUtils.addThaiAreaCode(request.getPhone()));
        Account response = service.get(token);
        return response.toAccountResponseM2(null);
    }

    public AccountResponseM2 updateAccountPicture(String token, MultipartFile file) throws AlphanahBaseException {
        PictureUtils.validateFile(file);
        service.update(token, CognitoField.IMAGE, amazonS3Service.saveFile(file));
        Account response = service.get(token);
        return response.toAccountResponseM2(null);
    }

}
