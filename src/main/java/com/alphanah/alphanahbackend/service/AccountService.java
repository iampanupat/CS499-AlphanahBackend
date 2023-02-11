package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Account;
import com.alphanah.alphanahbackend.exception.AccountException;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.enumerate.CognitoField;
import com.alphanah.alphanahbackend.enumerate.Role;
import com.alphanah.alphanahbackend.utility.PhoneUtils;
import com.alphanah.alphanahbackend.utility.JWTUtils;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
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
            GetUserResult userResult = cognitoClient.getUser(new GetUserRequest().withAccessToken(JWTUtils.removeBearer(token)));
            UUID accountUuid = UUID.fromString(userResult.getUserAttributes().get(0).getValue());
            return this.get(accountUuid);
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
                Date createDate = user.getUserCreateDate();
                Account account = this.mapping(new Account(), attributeTypes, createDate);;
                accountMap.put(account.getUuid(), account);
            }
            return accountMap;
        } catch (AmazonServiceException exception) {
            throw exception;
        }
    }

    private Account mapping(Account account, List<AttributeType> attributeTypes, Date createDate) {
        String bangkokTime = createDate.toInstant().atZone(ZoneId.of("Asia/Bangkok")).toString();
        bangkokTime = bangkokTime.substring(0, 29);
        account.setCreateDate(DateTime.parse(bangkokTime).toString());
        for (AttributeType attribute : attributeTypes) {
            CognitoField cognitoField = CognitoField.get(attribute.getName());
            String cognitoValue = attribute.getValue();
            if (Objects.isNull(cognitoField))
                continue;
            switch (cognitoField) {
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

    public void update(String token, CognitoField cognitoField, String value) throws AlphanahBaseException {
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
