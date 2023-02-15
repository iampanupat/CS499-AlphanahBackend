package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Account;
import com.alphanah.alphanahbackend.exception.AccountException;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.enumerate.AwsCognitoField;
import com.alphanah.alphanahbackend.enumerate.Role;
import com.alphanah.alphanahbackend.utility.DateUtils;
import com.alphanah.alphanahbackend.utility.Environment;
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

    private Map<UUID, Account> findAll() {
        try {
            List<UserType> users = cognitoClient.listUsers(new ListUsersRequest().withUserPoolId(userPoolId)).getUsers();
            Map<UUID, Account> accountMap = new HashMap<>();
            for (UserType user : users) {
                List<AttributeType> attributeTypes = user.getAttributes();
                Date createDate = user.getUserCreateDate();
                Account account = this.accountMapper(new Account(), attributeTypes, createDate);;
                accountMap.put(account.getUuid(), account);
            }
            return accountMap;
        } catch (AmazonServiceException exception) {
            throw exception;
        }
    }

    private Account accountMapper(Account account, List<AttributeType> attributeTypes, Date createDate) {
        account.setCreateDate(DateUtils.timeZoneConverter(createDate, Environment.bangkokZone));
        for (AttributeType attribute : attributeTypes) {
            AwsCognitoField awsCognitoField = AwsCognitoField.get(attribute.getName());
            String cognitoValue = attribute.getValue();
            if (Objects.isNull(awsCognitoField)) continue;
            switch (awsCognitoField) {
                case UUID -> account.setUuid(UUID.fromString(cognitoValue));
                case EMAIL -> account.setEmail(cognitoValue);
                case ROLE -> account.setRole(Role.valueOf(cognitoValue.toUpperCase()));
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

    public Account findAccount(String token) throws AlphanahBaseException {
        if (Objects.isNull(token))
            throw AccountException.cannotFindByNullToken();

        try {
            GetUserResult userResult = cognitoClient.getUser(new GetUserRequest().withAccessToken(JWTUtils.removeBearer(token)));
            UUID accountUuid = UUID.fromString(userResult.getUserAttributes().get(0).getValue());
            return this.findAccount(accountUuid);
        } catch (AmazonServiceException exception) {
            throw exception;
        }
    }

    public Account findAccount(UUID accountUuid) throws AlphanahBaseException {
        if (Objects.isNull(accountUuid))
            throw AccountException.cannotFindByNullAccountUuid();

        Map<UUID, Account> accountMap;
        try {
            accountMap = this.findAll();
        } catch (AmazonServiceException exception){
            throw exception;
        }

        Account account = accountMap.get(accountUuid);
        if (Objects.isNull(account))
            throw AccountException.notFound();
        return account;
    }

    public void updateAccount(String token, String firstname, String lastname, String address, String phone) throws AlphanahBaseException {
        if (Objects.isNull(token))
            throw AccountException.cannotUpdateWithNullToken();

        Account account = this.findAccount(token);
        this.updateAccount(account, firstname, lastname, address, phone);
    }

    public void updateAccount(UUID accountUuid, String firstname, String lastname, String address, String phone) throws AlphanahBaseException {
        if (Objects.isNull(accountUuid))
            throw AccountException.cannotUpdateWithNullAccountUuid();

        Account account = this.findAccount(accountUuid);
        this.updateAccount(account, firstname, lastname, address, phone);
    }

    public void updateAccount(Account account, String firstname, String lastname, String address, String phone) throws AlphanahBaseException {
        if (Objects.isNull(account))
            throw AccountException.cannotUpdateWithNullAccount();

        if (Objects.isNull(firstname))
            throw AccountException.cannotUpdateWithNullFirstname();

        if (Objects.isNull(lastname))
            throw AccountException.cannotUpdateWithNullLastname();

        if (Objects.isNull(address))
            throw AccountException.cannotUpdateWithNullAddress();

        if (Objects.isNull(phone))
            throw AccountException.cannotUpdateWithNullPhone();

        if (firstname.length() > Environment.FIRSTNAME_MAX_LENGTH)
            throw AccountException.cannotUpdateWithFirstnameExceedMaxLength();

        if (lastname.length() > Environment.LASTNAME_MAX_LENGTH)
            throw AccountException.cannotUpdateWithLastnameExceedMaxLength();

        if (address.length() > Environment.ADDRESS_MAX_LENGTH)
            throw AccountException.cannotUpdateWithAddressExceedMaxLength();

        if (phone.length() > Environment.PHONE_MAX_LENGTH)
            throw AccountException.cannotUpdateWithPhoneExceedMaxLength();

        this.updateAwsCognitoField(account, AwsCognitoField.FIRSTNAME, firstname);
        this.updateAwsCognitoField(account, AwsCognitoField.LASTNAME, lastname);
        this.updateAwsCognitoField(account, AwsCognitoField.ADDRESS, address);
        this.updateAwsCognitoField(account, AwsCognitoField.PHONE, phone);
    }

    public void updateAwsCognitoField(String token, AwsCognitoField awsCognitoField, String value) throws AlphanahBaseException {
        if (Objects.isNull(token))
            throw AccountException.cannotUpdateWithNullToken();

        Account account = this.findAccount(token);
        this.updateAwsCognitoField(account, awsCognitoField, value);
    }

    public void updateAwsCognitoField(UUID accountUuid, AwsCognitoField awsCognitoField, String value) throws AlphanahBaseException {
        if (Objects.isNull(accountUuid))
            throw AccountException.cannotUpdateWithNullAccountUuid();

        Account account = this.findAccount(accountUuid);
        this.updateAwsCognitoField(account, awsCognitoField, value);
    }

    public void updateAwsCognitoField(Account account, AwsCognitoField awsCognitoField, String value) throws AlphanahBaseException {
        if (Objects.isNull(account))
            throw AccountException.cannotUpdateWithNullAccount();

        if (Objects.isNull(awsCognitoField))
            throw AccountException.cannotUpdateWithNullAwsCognitoField();

        if (Objects.isNull(value))
            throw AccountException.cannotUpdateWithNullValue();

        if (value.length() > Environment.AWS_COGNITO_VALUE_MAX_LENGTH)
            throw AccountException.cannotUpdateWithAwsCognitoValueExceedMaxLength();

        try {
            cognitoClient.adminUpdateUserAttributes(new AdminUpdateUserAttributesRequest()
                    .withUsername(account.getEmail())
                    .withUserPoolId(userPoolId)
                    .withUserAttributes(new AttributeType().withName(awsCognitoField.getFieldName()).withValue(value))
            );
        } catch (AmazonServiceException exception) {
            throw exception;
        }
    }

}