package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Account;
import com.alphanah.alphanahbackend.exception.AccountException;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.enumerate.ECognitoField;
import com.alphanah.alphanahbackend.model.enumerate.ERole;
import com.alphanah.alphanahbackend.utility.PhoneUtils;
import com.alphanah.alphanahbackend.utility.JWTUtils;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {

    @Autowired
    private AWSCognitoIdentityProvider cognitoClient;

    @Value("${spring.security.oauth2.client.registration.cognito.userPoolId}")
    private String userPoolId;

    private final int AWS_COGNITO_VALUE_MAX_LENGTH = 2048;

    public Account get(String token) throws AlphanahBaseException {
        if (Objects.isNull(token))
            throw AccountException.getWithNullToken();

        try {
            List<AttributeType> attributeTypeList = cognitoClient.getUser(new GetUserRequest().withAccessToken(JWTUtils.removeBearer(token))).getUserAttributes();
            return this.mapping(new Account(), attributeTypeList);
        } catch (Exception exception) {
            throw exception;
        }
    }

    public Account get(UUID uuid) throws AlphanahBaseException {
        if (Objects.isNull(uuid))
            throw AccountException.getWithNullUuid();

        Map<UUID, Account> accountMap;
        try {
            accountMap = this.getAll();
        } catch (AmazonServiceException exception){
            throw exception;
        }

        Account account = accountMap.get(uuid);
        if (Objects.isNull(account))
            throw AccountException.getNullObject();

        return account;
    }

    private Map<UUID, Account> getAll() {
        try {
            List<UserType> users = cognitoClient.listUsers(new ListUsersRequest().withUserPoolId(userPoolId)).getUsers();
            Map<UUID, Account> accountMap = new HashMap<>();
            for (UserType user : users) {
                List<AttributeType> attributeTypes = user.getAttributes();
                Account account = this.mapping(new Account(), attributeTypes);;
                accountMap.put(account.getUuid(), account);
            }
            return accountMap;
        } catch (AmazonServiceException exception) {
            throw exception;
        }
    }

    private Account mapping(Account account, List<AttributeType> attributeTypes) {
        for (AttributeType attribute : attributeTypes) {
            ECognitoField cognitoField = ECognitoField.get(attribute.getName());
            String cognitoValue = attribute.getValue();
            if (Objects.isNull(cognitoField))
                continue;
            switch (cognitoField) {
                case UUID -> account.setUuid(UUID.fromString(cognitoValue));
                case EMAIL -> account.setEmail(cognitoValue);
                case ROLE -> account.setRole(ERole.valueOf(cognitoValue.toUpperCase()));
                case FIRSTNAME -> account.setFirstname(cognitoValue);
                case LASTNAME -> account.setLastname(cognitoValue);
                case ADDRESS -> account.setAddress(cognitoValue);
                case PHONE -> account.setPhone(PhoneUtils.removeThaiAreaCode(cognitoValue));
                case IMAGE -> account.setImage(cognitoValue);
                case CART_UUID -> account.setCartUuid(UUID.fromString(cognitoValue));
            }
        }
        return account;
    }

    public void update(String token, ECognitoField cognitoField, String value) throws AlphanahBaseException {
        if (Objects.isNull(token))
            throw AccountException.updateWithNullToken();

        if (Objects.isNull(cognitoField))
            throw AccountException.updateWithNullCognitoField();

        if (Objects.isNull(value))
            throw AccountException.updateWithNullValue();

        if (value.isEmpty())
            throw AccountException.updateWithEmptyValue();

        if (value.length() > AWS_COGNITO_VALUE_MAX_LENGTH)
            throw AccountException.updateWithMaxLengthValue();

        Account account;
        try {
            account = this.get(token);
        } catch (AlphanahBaseException exception) {
            throw AccountException.updateNullObject();
        }

        try {
            cognitoClient.adminUpdateUserAttributes(new AdminUpdateUserAttributesRequest()
                    .withUsername(account.getEmail())
                    .withUserPoolId(userPoolId)
                    .withUserAttributes(new AttributeType().withName(cognitoField.getFieldName()).withValue(value))
            );
        } catch (AmazonServiceException exception) {
            throw exception;
        }
    }

}
